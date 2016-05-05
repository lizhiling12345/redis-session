package com.radiadesign.catalina.session;

import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Logger;

public class PureSessionHandlerValve extends ValveBase {

	private PureSessionManager manager;

	public void setPureSessionManager(PureSessionManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void invoke(Request arg0, Response arg1) throws IOException,
			ServletException {
		try {  
			//debug信息
			try {
				if( this.manager!=null && "debug".equals( this.manager.getExecMode() ) ){
					System.out.println("PureSessionHandlerValve:"+arg0.getRequestURL());
				}
			} catch (Exception e) {
				
			}
			
		      getNext().invoke(arg0, arg1);  
		} finally {  
		      final Session session =arg0.getSessionInternal(false);
		      
				//debug信息
				try {
					if( this.manager!=null && "debug".equals( this.manager.getExecMode() ) ){
						System.out.println("PureSessionHandlerValve:"+session.getId()+":"+arg0.getRequestURL());
					}
				} catch (Exception e) {
					
				}
				
		      this.manager.afterRequest();
		}
	}

}
