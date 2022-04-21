package fr.paris.lutece.plugins.forms.modules.extend.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;

import fr.paris.lutece.plugins.extend.business.extender.history.ResourceExtenderHistory;
import fr.paris.lutece.plugins.extend.service.extender.facade.ExtenderType;
import fr.paris.lutece.plugins.extend.service.extender.facade.IExtendableResourceResult;
import fr.paris.lutece.plugins.extend.service.extender.facade.ResourceExtenderServiceFacade;
import fr.paris.lutece.plugins.extend.service.extender.history.IResourceExtenderHistoryService;
import fr.paris.lutece.plugins.extend.service.extender.history.ResourceExtenderHistoryService;
import fr.paris.lutece.plugins.forms.business.FormResponse;
import fr.paris.lutece.plugins.forms.business.form.search.FormResponseSearchItem;
import fr.paris.lutece.plugins.forms.service.search.ILucenDocumentExternalFieldProvider;
import fr.paris.lutece.portal.service.search.SearchItem;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class ExtendLuceneExternalFieldProvider implements ILucenDocumentExternalFieldProvider {

	@Override
	public void provideFields(List<Document> documentList) {
		
		Map<String, List<Document> > mapDoc= documentList.stream().collect(Collectors.groupingBy(doc-> doc.get(FormResponseSearchItem.FIELD_ID_FORM)));

		IResourceExtenderHistoryService historyService=SpringContextService.getBean( ResourceExtenderHistoryService.BEAN_SERVICE );
		for( Entry<String, List<Document>> entry: mapDoc.entrySet()) {
			
			List<String> listIdExtendableResource= entry.getValue().stream().map(doc -> doc.get(SearchItem.FIELD_UID)).collect(Collectors.toList());
			for ( ExtenderType<? extends IExtendableResourceResult> extender : ResourceExtenderServiceFacade.getListExtenderType( ) )
		    {		       
		    	List<ResourceExtenderHistory> listResourceExtenderHistory= historyService.findByListIdResource(listIdExtendableResource, FormResponse.RESOURCE_TYPE+"_"+entry.getKey(), extender.getType());
		        addFieldsToDocument(documentList,listResourceExtenderHistory, extender );      
		     }  
		}

	}
	
	private void addFieldsToDocument(List<Document>  documentList,  List<ResourceExtenderHistory> listResourceExtenderHistory, ExtenderType<? extends IExtendableResourceResult> extender ) 
	{
		long count=0;
		String strKey=extender.getType()+Constants.COULUMN_NAME_SUFIX;
		for(Document doc: documentList) 
		{
			count =listResourceExtenderHistory.stream().filter(elt -> elt.getIdExtendableResource().equals( doc.get(SearchItem.FIELD_UID) ) ).count() ;
	        doc.add( new LongPoint( strKey , count));
	        doc.add( new NumericDocValuesField( strKey, count ) );
	        doc.add( new StoredField( strKey, count ) );
    
		}
	}


}
