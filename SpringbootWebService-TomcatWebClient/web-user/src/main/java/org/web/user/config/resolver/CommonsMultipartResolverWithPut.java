package org.web.user.config.resolver;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class CommonsMultipartResolverWithPut extends org.springframework.web.multipart.commons.CommonsMultipartResolver {

    public CommonsMultipartResolverWithPut() {
        super();
    }

    public CommonsMultipartResolverWithPut(ServletContext servletContext) {
        this();
        setServletContext(servletContext);
    }

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        return !"POST".equalsIgnoreCase(request.getMethod()) && !"PUT".equalsIgnoreCase(request.getMethod()) ? false : FileUploadBase.isMultipartContent(new ServletRequestContext(request));
    }

}
