package com.mycompany.templateresourcereferencetestapplication;

import java.util.HashMap;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.protocol.https.Scheme;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.resource.TextTemplateResourceReference;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.mycompany.templateresourcereferencetestapplication.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
                mountResource("css/mail.css", new TextTemplateResourceReference(WicketApplication.class, "css/mail.css", "text/css", "utf8", new Model(new HashMap<String, Object>())));
		// add your configuration here
                            setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig()) {

                @Override
                protected Scheme getDesiredSchemeFor(Class<? extends IRequestablePage> pageClass) {
                    if (getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
  //                      LOG.info("Dev mode, always use HTTP");
                        return Scheme.HTTP;
                    } else {                        
                        Scheme sm=super.getDesiredSchemeFor(pageClass);
                        sm=Scheme.HTTPS;
//                        LOG.info("not in development mode "+sm.name());
                        return sm;
                    }
                }

            });

                
                
                
	}
}
