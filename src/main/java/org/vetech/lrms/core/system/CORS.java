//package org.vetech.lrms.core.system;
//
//import com.sun.jersey.spi.container.ContainerRequest;
//import com.sun.jersey.spi.container.ContainerResponse;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.core.Response;
//import java.io.IOException;
//
///**
// * Created by alex on 2/9/15.
// */
//public class CORS implements ContainerResponseFilter {
//	@Override
//	public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {
//
//		Response.ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
//		resp.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//
//		String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
//
//		if(null != reqHead && !reqHead.equals("")){
//			resp.header("Access-Control-Allow-Headers", reqHead);
//		}
//
//		contResp.setResponse(resp.build());
//		return contResp;
//	}
//
//	@Override
//	public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext)
//			throws IOException {
//
//	}
//}
