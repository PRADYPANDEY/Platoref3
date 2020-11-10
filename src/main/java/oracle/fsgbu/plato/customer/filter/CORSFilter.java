/*----------------------------- OFSS-COPYRIGHT-BEGIN ----------------------------- 
* 
* This source is part of the Oracle Banking Software Product. 
* Copyright  2018 , 2018 , Oracle and/or its affiliates.  All rights reserved.
* 
* No part of this work may be reproduced, stored in a retrieval system, adopted or transmitted in any form or by any means, electronic, mechanical, photographic, graphic, optic recording or otherwise, translated in any language or computer language, without the prior written permission of Oracle and/or its affiliates.
* 
* Oracle Financial Services Software Limited.
* Oracle Park, Off Western Express Highway,
* Goregaon (East), 
* Mumbai - 400 063, India.
* 
* ------------------------------- OFSS-COPYRIGHT-END -----------------------------
*/ 
/*-----------------------------------------------------------------------------------------------------
  **
  ** File Name  : CORSFilter.java
  **
  ** Module     : 
  ** 
  -------------------------------------------------------------------------------------------------------
<<<<<<< HEAD

  CHANGE HISTORY

  Created By         :  System

=======
 
  CHANGE HISTORY
 
  Created By         :  System
 
>>>>>>> development
  Bug Number         :  
  Changed By         :  
  Change Description :  
  Change On          :  
  Search String      :  
<<<<<<< HEAD
  
=======
 
>>>>>>> development
  -------------------------------------------------------------------------------------------------------
  */
package oracle.fsgbu.plato.customer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
    public static final String VALID_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH";

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("---CORS Configuration Beginning---");
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String origin = httpRequest.getHeader("Origin");
        String headers = httpRequest.getHeader("Access-Control-Request-Headers");
        httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpResponse.setHeader("Access-Control-Allow-Headers", headers);
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", VALID_METHODS);

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            System.out.println("---CORS Configuration Completed INSIDE OPTIONS---");
            origin = httpRequest.getHeader("Origin");
            headers = httpRequest.getHeader("Access-Control-Request-Headers");
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpResponse.setHeader("Access-Control-Allow-Headers", headers);
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Methods", VALID_METHODS);
            return;
        }

        System.out.println("---CORS Configuration Completed---");
        chain.doFilter(request, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}