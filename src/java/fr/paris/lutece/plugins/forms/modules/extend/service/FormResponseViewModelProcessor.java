/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.forms.modules.extend.service;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.extend.business.extender.ResourceExtenderDTO;
import fr.paris.lutece.plugins.extend.business.extender.ResourceExtenderDTOFilter;
import fr.paris.lutece.plugins.extend.service.extender.IResourceExtenderService;
import fr.paris.lutece.plugins.extend.service.extender.ResourceExtenderService;
import fr.paris.lutece.plugins.extend.web.component.IResourceExtenderComponent;
import fr.paris.lutece.plugins.extend.web.component.IResourceExtenderComponentManager;
import fr.paris.lutece.plugins.extend.web.component.ResourceExtenderComponentManager;
import fr.paris.lutece.plugins.forms.business.FormResponse;
import fr.paris.lutece.plugins.forms.business.FormResponseHome;
import fr.paris.lutece.plugins.forms.web.form.response.view.IFormResponseViewModelProcessor;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class FormResponseViewModelProcessor implements IFormResponseViewModelProcessor
{
    @Override
    public void populateModel( HttpServletRequest request, Map<String, Object> mapModel, int nIdFormResponse, Locale locale )
    {

        ResourceExtenderDTO resourceExtender = new ResourceExtenderDTO( );
        FormResponse formResponse = FormResponseHome.loadById( nIdFormResponse );
        resourceExtender.setIdExtendableResource( String.valueOf( nIdFormResponse ) );
        resourceExtender.setExtendableResourceType( formResponse.getExtendableResourceType( ) );
        resourceExtender.setName( formResponse.getExtendableResourceName( ) );      
        IResourceExtenderService resourceExtenderService = SpringContextService.getBean( ResourceExtenderService.BEAN_SERVICE );
        ResourceExtenderDTOFilter filter = new ResourceExtenderDTOFilter();
        filter.setFilterExtendableResourceType( resourceExtender.getExtendableResourceType( ));
        IResourceExtenderComponentManager extenderComponentManager = SpringContextService.getBean( ResourceExtenderComponentManager.BEAN_MANAGER );
        if ( extenderComponentManager != null )
        {
            IResourceExtenderComponent resourceExtendercomponent = null;
            for ( ResourceExtenderDTO extender : resourceExtenderService.findByFilter(filter) )
            {
                resourceExtender.setExtenderType( extender.getExtenderType() );
                resourceExtendercomponent = extenderComponentManager.getResourceExtenderComponent( extender.getExtenderType() );
                if ( resourceExtendercomponent != null )
                {
                    mapModel.put( extender.getExtenderType(), resourceExtendercomponent.getInfoHtml( resourceExtender, locale, request ) );
                }

            }
        }
    }

}
