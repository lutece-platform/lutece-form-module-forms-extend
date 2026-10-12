/*
 * Copyright (c) 2002-2026, City of Paris
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
package fr.paris.lutece.plugins.forms.modules.extend.business.form.culumn.impl;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.paris.lutece.plugins.forms.business.form.column.IFormColumn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

/**
 * Produces the multiview columns of the form responses for the rating, comment and hit extenders.
 */
@ApplicationScoped
public class FormColumnFormResponseExtendProducer
{
    /**
     * Produces the rating column.
     *
     * @param nPosition
     *            the column position
     * @param strFormColumnTitle
     *            the i18n key of the column title
     * @return the rating column
     */
    @Produces
    @ApplicationScoped
    @Named( "forms-extend.formResponseExtendRating.column" )
    public IFormColumn produceFormColumnExtendRating( @ConfigProperty( name = "forms-extend.formResponseExtendRating.column.position" ) int nPosition,
            @ConfigProperty( name = "forms-extend.formResponseExtendRating.column.formColumnTitle" ) String strFormColumnTitle )
    {
        return new FormColumnFormResponseExtend( "rating", nPosition, strFormColumnTitle );
    }

    /**
     * Produces the comment column.
     *
     * @param nPosition
     *            the column position
     * @param strFormColumnTitle
     *            the i18n key of the column title
     * @return the comment column
     */
    @Produces
    @ApplicationScoped
    @Named( "forms-extend.formResponseExtendComment.column" )
    public IFormColumn produceFormColumnExtendComment( @ConfigProperty( name = "forms-extend.formResponseExtendComment.column.position" ) int nPosition,
            @ConfigProperty( name = "forms-extend.formResponseExtendComment.column.formColumnTitle" ) String strFormColumnTitle )
    {
        return new FormColumnFormResponseExtend( "comment", nPosition, strFormColumnTitle );
    }

    /**
     * Produces the hit column.
     *
     * @param nPosition
     *            the column position
     * @param strFormColumnTitle
     *            the i18n key of the column title
     * @return the hit column
     */
    @Produces
    @ApplicationScoped
    @Named( "forms-extend.formResponseExtendHit.column" )
    public IFormColumn produceFormColumnExtendHit( @ConfigProperty( name = "forms-extend.formResponseExtendHit.column.position" ) int nPosition,
            @ConfigProperty( name = "forms-extend.formResponseExtendHit.column.formColumnTitle" ) String strFormColumnTitle )
    {
        return new FormColumnFormResponseExtend( "hit", nPosition, strFormColumnTitle );
    }
}
