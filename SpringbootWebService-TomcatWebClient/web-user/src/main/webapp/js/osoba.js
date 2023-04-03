let rowId;
let dialog = document.getElementById('dialog');
let preview = document.querySelector('#preview');
let file = document.querySelector('#file');
let uploadForm = document.querySelector('#uploadForm');
let imgEmpty = $('#uploadForm input[id=img-empty]');
let table = document.getElementById("table-user");
let updater = false; let tableRow = null;
let prot = window.location.protocol;
let host = window.location.host;

let createClickHandler =
	function (row) {
		return function () {
			updater = true; tableRow = row;
			$('#uploadForm input[id=name]').val(row.getElementsByTagName("td")[0].innerHTML);
			$('#uploadForm input[id=surname]').val(row.getElementsByTagName("td")[1].innerHTML);
			$('#uploadForm input[id=homePhone]').val(row.getElementsByTagName("td")[2].innerHTML);
			$('#uploadForm input[id=officePhone]').val(row.getElementsByTagName("td")[3].innerHTML);
			$('#uploadForm input[id=eMail]').val(row.getElementsByTagName("td")[4].innerHTML);
			let imgCell = row.getElementsByTagName("td")[5];
			let img = imgCell.getElementsByTagName("img")[0];
			previewImage(img);
			imgEmpty.checked = false;
			rowId = row.id;
			dialog.style.display = "block";
		}
	}

let clickHandlerA =
	function (a) {
		return function () {
			deleteRow(a.rel, a);
		}
	}

function addRowHandlers() {
	let rows = table.tBodies[0].getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		let currentRow = rows[i];
		currentRow.ondblclick = createClickHandler(currentRow);

		let cell = currentRow.getElementsByTagName("td")[6];
		let a = cell.getElementsByTagName("a")[0];
		a.onclick = clickHandlerA(a);
	}
}

window.onload = addRowHandlers();

$(document).ready(function () {
	imgEmpty.change(function (event) {
		file.value = "";
		file.disabled = !!imgEmpty.is(":checked");
	});

	$('#add').click(function (event) {
		updater = false;
		preview.innerHTML = "";
		file.value = "";
		$('#name').val("");
		$('#surname').val("");
		$('#homePhone').val("");
		$('#officePhone').val("");
		$('#eMail').val("");
		imgEmpty.checked = false;

		dialog.style.display = "block";
	});

	$('#cancel').click(function (event) {
		dialog.style.display = "none";
		tableRow = null;
	});
});

function previewImage(img) {
	preview.innerHTML = "";
	file.value = "";

	if (img.alt !== "") {
		let image = new Image();
		image.height = 150; image.width = 150;
		image.title = img.alt;
		image.alt = img.alt;
		image.src = img.src;
		preview.appendChild(image);
	}
}

function previewImageLoad() {
	preview.innerHTML = "";
	if (this.files) {
		[].forEach.call(this.files, readAndPreview);
	}

	function readAndPreview(file) {
		if (!/\.(jpe?g|png|gif)$/i.test(file.name)) {
			return alert(file.name + " is not an image");
		}
		let reader = new FileReader();
		reader.addEventListener("load", function () {
			let image = new Image();
			image.height = 150; image.width = 150;
			image.title = file.name;
			image.alt = "img-" + file.name;
			image.src = this.result;

			preview.appendChild(image);
		});
		reader.readAsDataURL(file);
	}
}

file.addEventListener("change", previewImageLoad);

function send(file) {
	let formData = new FormData();
	if (file !== null) {
		formData.append("file", file);
	}
	let json = JSON.stringify({
		"id": null, "name": $('#uploadForm input[id=name]').val(), "surname": $('#uploadForm input[id=surname]').val(),
		"homePhone": $('#uploadForm input[id=homePhone]').val(), "officePhone": $('#uploadForm input[id=officePhone]').val(),
		"email": $('#uploadForm input[id=eMail]').val(), "photoOfAPerson": null
	});
	formData.append("vm", json);

	let xhr = new XMLHttpRequest();
	xhr.open("POST", prot + "//" + host + "/form/");
	xhr.onload = function () {
		console.log(xhr.status + ", " + xhr.response)
		if (xhr.status === 200) {
			let response = JSON.parse(xhr.responseText);
			let body = table.getElementsByTagName('tbody')[0];
			let row = body.insertRow(); row.setAttribute("id", response.id);
			row.setAttribute("class", "tr-hover-class"); row.ondblclick = createClickHandler(row);
			let cell = row.insertCell(0); cell.innerHTML = response.name;
			cell = row.insertCell(1); cell.innerHTML = response.surname;
			cell = row.insertCell(2); cell.innerHTML = response.homePhone;
			cell = row.insertCell(3); cell.innerHTML = response.officePhone;
			cell = row.insertCell(4); cell.innerHTML = response.email;
			cell = row.insertCell(5);
			if (response.photoOfAPerson !== null) {
				let img = document.createElement('img');
				img.src = "data:image/png;base64, " + response.photoOfAPerson;
				img.alt = "img-" + response.id;
				img.width = 150; img.height = 150;
				cell.appendChild(img);
			} else {
				let img = document.createElement('img');
				img.src = ""; img.alt = "";
				cell.appendChild(img);
			}
			cell = row.insertCell(6);
			let a = document.createElement('a');
			a.id = table.rows.length - 2;
			a.rel = response.id; a.tagName = "delete";
			a.innerHTML = "Delete"; a.onclick = clickHandlerA(a);
			a.setAttribute("style", "cursor: pointer;");
			cell.appendChild(a);
		} else {
			let state = document.getElementById("state");
			state.innerHTML = "Some Error Occurred";
		}
	}
	xhr.send(formData);
}

function update(file, row) {
	let formData = new FormData();
	if (file !== null) {
		formData.append("file", file);
	} else {
		const img = preview.getElementsByTagName("img")[0];
		if(!imgEmpty.is(":checked") && img !== null && img !== undefined && img.alt !== ""){
			fetch(img.src)
				.then(res => res.blob())
				.then(blob => {
					const file_ = new File([blob], "img-" + row.id + "-" + img.alt, blob);
					formData.append("file", file_);
					file = file_;
				})
		}
	}
	let json = JSON.stringify({
		"id": row.id, "name": $('#uploadForm input[id=name]').val(), "surname": $('#uploadForm input[id=surname]').val(),
		"homePhone": $('#uploadForm input[id=homePhone]').val(), "officePhone": $('#uploadForm input[id=officePhone]').val(),
		"email": $('#uploadForm input[id=eMail]').val(), "photoOfAPerson": null
	});
	formData.append("vm", json);

	let xhr = new XMLHttpRequest();
	xhr.open("PUT", prot + "//" + host + "/form/");
	xhr.onload = function () {
		if (xhr.status === 200) {
			row.getElementsByTagName("td")[0].innerHTML = $('#uploadForm input[id=name]').val();
			row.getElementsByTagName("td")[1].innerHTML = $('#uploadForm input[id=surname]').val();
			row.getElementsByTagName("td")[2].innerHTML = $('#uploadForm input[id=homePhone]').val();
			row.getElementsByTagName("td")[3].innerHTML = $('#uploadForm input[id=officePhone]').val();
			row.getElementsByTagName("td")[4].innerHTML = $('#uploadForm input[id=eMail]').val();
			if (file !== null) {
				let img = document.createElement('img');
				img.src = window.URL.createObjectURL(file);
				img.alt = "img-" + row.id;
				img.width = 150; img.height = 150;
				row.getElementsByTagName("td")[5].innerHTML = "";
				row.getElementsByTagName("td")[5].appendChild(img);
			} else {
				let img = document.createElement('img');
				img.src = ""; img.alt = "";
				row.getElementsByTagName("td")[5].innerHTML = "";
				row.getElementsByTagName("td")[5].appendChild(img);
			}
		} else {
			let state = document.getElementById("state");
			state.innerHTML = "Some Error Occurred";
		}
	}
	xhr.send(formData);
}

function deleteRow_(a) {
	let row = a.parentNode.parentNode;
	row.parentNode.removeChild(row);
}

function deleteRow(y, a) {
	let xhr = new XMLHttpRequest();
	xhr.open("DELETE", prot + "//" + host + "/form/" + y);
	xhr.onload = function () {
		if (xhr.status === 200) {
			deleteRow_(a);
		} else {
			let state = document.getElementById("state");
			state.innerHTML = "Some Error Occurred";
		}
	}
	xhr.send();
}

uploadForm.addEventListener('submit', function (event) {
	let files = file.files;
	if (!updater) {
		if (files.length === 0) {
			send(null);
		} else {
			send(files[0]);
		}
	} else {
		if (files.length === 0) {
			update(null, tableRow);
		} else {
			update(files[0], tableRow);
		}
	}
	event.preventDefault();
}, true);
