package com.radiadesign.catalina.session;

import java.security.Principal;
import org.apache.catalina.Manager;
import org.apache.catalina.session.StandardSession;
import java.util.HashMap;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Logger;

public class PureSession extends StandardSession {

	public PureSession(Manager manager) {
		super(manager);
	}

}
