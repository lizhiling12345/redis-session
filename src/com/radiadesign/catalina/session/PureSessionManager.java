package com.radiadesign.catalina.session;

import org.apache.catalina.Globals;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.util.LifecycleSupport;
import org.apache.catalina.Loader;
import org.apache.catalina.Valve;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PureSessionManager extends ManagerBase implements Lifecycle {
	
	protected PureSessionHandlerValve handlerValve;
	protected String execMode="";//根据这个数值，打印后台信息,debug
	protected String userProperties="";//用于程序根据这个值执行不同的功能（用于共享代码的情况）
	
	public String getExecMode() {
		return execMode;
	}

	public void setExecMode(String execMode) {
		this.execMode = execMode;
	}

	public String getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(String userProperties) {
		this.userProperties = userProperties;
	}
	
	/**
	 * The lifecycle event support for this component.
	 */
	protected LifecycleSupport lifecycle = new LifecycleSupport(this);
	
	@Override
	public void addLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		lifecycle.addLifecycleListener(arg0);
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return lifecycle.findLifecycleListeners();
	}

	@Override
	public void removeLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		lifecycle.removeLifecycleListener(arg0);
	}

	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		
		for (Valve valve : getContainer().getPipeline().getValves()) {
			if (valve instanceof PureSessionHandlerValve) {
				this.handlerValve = (PureSessionHandlerValve) valve;
				this.handlerValve.setPureSessionManager(this);
				log.info("PureSessionManager:Attached to PureSessionHandlerValve");
				
				if( getUserProperties()!=null ){
					System.setProperty("userProperties", getUserProperties());
					log.info("PureSessionManager:userProperties:"+getUserProperties());
				}
				
				break;
			}
		}

		log.info("PureSessionManager:Will expire sessions after " + getMaxInactiveInterval()+ " seconds");
		
		lifecycle.fireLifecycleEvent(START_EVENT, null);
	}

	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		
		lifecycle.fireLifecycleEvent(STOP_EVENT, null);
	}

	@Override
	public int getRejectedSessions() {
		// TODO Auto-generated method stub
		//Do nothing.
		return 0;
	}

	@Override
	public void load() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		//Do nothing.
	}

	@Override
	public void setRejectedSessions(int arg0) {
		// TODO Auto-generated method stub
		//Do nothing.
	}

	@Override
	public void unload() throws IOException {
		// TODO Auto-generated method stub
		//Do nothing.
	}

	
	@Override
	public Session createSession() {
		return super.createSession();
	}
	
	@Override
	public Session createEmptySession() {
		return super.createEmptySession();
	}

	@Override
	public void add(Session session) {
		super.add(session);
	}

	@Override
	public Session findSession(String id) throws IOException {
		return super.findSession(id);
	}

	
	public void afterRequest() {
		//
		
	}
	
}
