<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Categories Detail</title>
            <link rel="stylesheet" type="text/css" href="/FIS-GC0804-S4-G1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Categories Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Cid:"/>
                    <h:outputText value="#{categories.categories.cid}" title="Cid" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{categories.categories.name}" title="Name" />

                    <h:outputText value="ProductsList:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty categories.categories.productsList}" value="(No Items)"/>
                        <h:dataTable value="#{categories.categories.productsList}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty categories.categories.productsList}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Pid"/>
                                </f:facet>
                                <h:outputText value="#{item.pid}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{item.code}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value="#{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Infomations"/>
                                </f:facet>
                                <h:outputText value="#{item.infomations}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Price"/>
                                </f:facet>
                                <h:outputText value="#{item.price}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ImagePath"/>
                                </f:facet>
                                <h:outputText value="#{item.imagePath}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="CreateAt"/>
                                </f:facet>
                                <h:outputText value="#{item.createAt}">
                                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cid"/>
                                </f:facet>
                                <h:outputText value="#{item.cid}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{products.detailSetup}">
                                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="categories" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.CategoriesController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{products.editSetup}">
                                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="categories" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.CategoriesController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{products.destroy}">
                                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="categories" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.CategoriesController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{categories.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{categories.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{categories.createSetup}" value="New Categories" />
                <br />
                <h:commandLink action="#{categories.listSetup}" value="Show All Categories Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
