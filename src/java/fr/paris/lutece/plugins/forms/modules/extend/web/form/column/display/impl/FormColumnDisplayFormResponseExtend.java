package fr.paris.lutece.plugins.forms.modules.extend.web.form.column.display.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.extend.service.extender.facade.ExtenderType;
import fr.paris.lutece.plugins.extend.service.extender.facade.IExtendableResourceResult;
import fr.paris.lutece.plugins.extend.service.extender.facade.ResourceExtenderServiceFacade;
import fr.paris.lutece.plugins.forms.business.form.column.FormColumnCell;
import fr.paris.lutece.plugins.forms.modules.extend.service.Constants;
import fr.paris.lutece.plugins.forms.web.form.column.display.impl.AbstractFormColumnDisplay;
import fr.paris.lutece.portal.service.template.AppTemplateService;

public class FormColumnDisplayFormResponseExtend extends AbstractFormColumnDisplay{
    private static final String FORM_COLUMN_HEADER_TEMPLATE = "admin/plugins/forms/modules/extend/column/form_column_form_response_extend_header.html";
	 // Marks
    private static final String MARK_FORM_RESPONSE_EXTEND_COLUMN_TITLE = "column_title";
    private static final String MARK_SORT_URL = "sort_url";
    private static final String MARK_COLUMN_SORT_ATTRIBUTE = "column_sort_attribute";
    private String strExtenderType;
    
    public FormColumnDisplayFormResponseExtend(String strExtenderType) {
    	
    	this.strExtenderType= strExtenderType;
    }
	@Override
	public String buildFormColumnHeaderTemplate(String strSortUrl, Locale locale) {
		 
		 Map<String, Object> model = new LinkedHashMap<>( );
	        model.put( MARK_SORT_URL, buildCompleteSortUrl( strSortUrl ) );
	        model.put( MARK_FORM_RESPONSE_EXTEND_COLUMN_TITLE, getFormColumnTitle( locale ) );
	        model.put( MARK_COLUMN_SORT_ATTRIBUTE, strExtenderType + Constants.COULUMN_NAME_SUFIX );

	        String strFormResponseAssigneeHeaderTemplate = AppTemplateService.getTemplate( FORM_COLUMN_HEADER_TEMPLATE, locale, model ).getHtml( );
	        setFormColumnHeaderTemplate( strFormResponseAssigneeHeaderTemplate );

	        return strFormResponseAssigneeHeaderTemplate;
	}

	@Override
	public String buildFormColumnCellTemplate(FormColumnCell formColumnCell, Locale locale) {
		StringBuilder builder= new StringBuilder("<td>");
        if ( formColumnCell != null )
        {
        	 for ( ExtenderType<? extends IExtendableResourceResult> extender : ResourceExtenderServiceFacade.getListExtenderType( ) )
     	    {
        		 Object value=StringUtils.EMPTY;
        		 if( extender.getType( ).equals(this.strExtenderType )) {
        			 value= formColumnCell.getFormColumnCellValueByName( extender.getType( ) );
        		 }
        		 builder.append( value != null ? value:StringUtils.EMPTY);
     	    }
            
        }

       return builder.append("</td>").toString( );
	}

}
