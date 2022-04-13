package fr.paris.lutece.plugins.forms.modules.extend.business.form.culumn.impl;


import fr.paris.lutece.plugins.forms.business.form.column.impl.AbstractFormColumn;
import fr.paris.lutece.plugins.forms.business.form.column.querypart.IFormColumnQueryPart;
import fr.paris.lutece.plugins.forms.modules.extend.business.form.querypart.imp.FormColumnFormResponseExtendQueryPart;
import fr.paris.lutece.plugins.forms.modules.extend.web.form.column.display.impl.FormColumnDisplayFormResponseExtend;
import fr.paris.lutece.plugins.forms.web.form.column.display.IFormColumnDisplay;

public class FormColumnFormResponseExtend extends AbstractFormColumn {

	public String strExtenderType ;
	public FormColumnFormResponseExtend( String strExtenderType, int nFormColumnPosition, String strFormColumnTitle ) {
		
		 super( );
		 this.strExtenderType= strExtenderType;
	     setFormColumnPosition( nFormColumnPosition );
	     setFormColumnTitle( strFormColumnTitle );
	}
	@Override
	public IFormColumnQueryPart getFormColumnQueryPart() {
		
        return new FormColumnFormResponseExtendQueryPart( this.strExtenderType);
	}

	@Override
	public IFormColumnDisplay getFormColumnDisplay() {
		
		 FormColumnDisplayFormResponseExtend formColumnDisplayFormResponse = new FormColumnDisplayFormResponseExtend( strExtenderType );
	        formColumnDisplayFormResponse.setFormColumn( this );
	        return formColumnDisplayFormResponse;
	}
	
}
