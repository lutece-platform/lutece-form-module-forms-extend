package fr.paris.lutece.plugins.forms.modules.extend.business.form.querypart.imp;

import java.util.HashMap;
import java.util.Map;


import fr.paris.lutece.plugins.extend.service.extender.facade.ExtenderType;
import fr.paris.lutece.plugins.extend.service.extender.facade.IExtendableResourceResult;
import fr.paris.lutece.plugins.extend.service.extender.facade.InfoExtenderException;
import fr.paris.lutece.plugins.extend.service.extender.facade.ResourceExtenderServiceFacade;
import fr.paris.lutece.plugins.forms.business.FormResponse;
import fr.paris.lutece.plugins.forms.business.form.column.querypart.impl.AbstractFormColumnQueryPart;
import fr.paris.lutece.plugins.forms.business.form.search.FormResponseSearchItem;
import fr.paris.lutece.portal.service.util.AppLogService;

public class FormColumnFormResponseExtendQueryPart extends AbstractFormColumnQueryPart {

	private String strExtenderType;
	
	public FormColumnFormResponseExtendQueryPart( String extenderType ) {
		
		this.strExtenderType= extenderType; 
	}
	@Override
	protected Map<String, Object> getMapFormColumnValues(FormResponseSearchItem formResponseSearchItem) {
		
		Map<String, Object> mapFormColumnValues = new HashMap<>( ); 
		
	    for ( ExtenderType<? extends IExtendableResourceResult> extender : ResourceExtenderServiceFacade.getListExtenderType( ) )
	    {
	    	if(extender.getType().equals( this.strExtenderType ) ) {
		        try {     
		       	   	 mapFormColumnValues.put( extender.getType( ), extender.getInfoForRecap( String.valueOf( formResponseSearchItem.getIdFormResponse( )), FormResponse.RESOURCE_TYPE+"_"+formResponseSearchItem.getIdForm( )) );
		            	
		        } catch (InfoExtenderException e) {
	             	
	            	AppLogService.error ( e.getMessage( ), e );
			    }
	    	}
	    }
	        return mapFormColumnValues;
	}

}
