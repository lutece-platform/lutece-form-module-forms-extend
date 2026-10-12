package fr.paris.lutece.plugins.forms.modules.extend.web.form.column.display.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.extend.modules.hit.service.extender.HitResourceExtender;
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
    private static final String HIT_COUNT_NONE = "0";
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

	/**
	 * Builds the cell of the extender: its recap value, or 0 views for a resource the hit extender has never counted.
	 *
	 * @param formColumnCell the cell values of the response
	 * @param locale the locale
	 * @return the cell HTML
	 */
	@Override
	public String buildFormColumnCellTemplate(FormColumnCell formColumnCell, Locale locale) {
		Object value = formColumnCell != null ? formColumnCell.getFormColumnCellValueByName( strExtenderType ) : null;
		if ( formColumnCell != null && HitResourceExtender.EXTENDER_TYPE.equals( strExtenderType ) && StringUtils.isEmpty( Objects.toString( value, null ) ) )
		{
			value = HIT_COUNT_NONE;
		}

		return "<td>" + Objects.toString( value, StringUtils.EMPTY ) + "</td>";
	}

}
