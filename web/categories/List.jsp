<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Categories Items</title>
            <link rel="stylesheet" type="text/css" href="/FIS-GC0804-S4-G1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Categories Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Categories Items Found)<br />" rendered="#{categories.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{categories.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{categories.pagingInfo.firstItem + 1}..#{categories.pagingInfo.lastItem} of #{categories.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{categories.prev}" value="Previous #{categories.pagingInfo.batchSize}" rendered="#{categories.pagingInfo.firstItem >= categories.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{categories.next}" value="Next #{categories.pagingInfo.batchSize}" rendered="#{categories.pagingInfo.lastItem + categories.pagingInfo.batchSize <= categories.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{categories.next}" value="Remaining #{categories.pagingInfo.itemCount - categories.pagingInfo.lastItem}"
                                   rendered="#{categories.pagingInfo.lastItem < categories.pagingInfo.itemCount && categories.pagingInfo.lastItem + categories.pagingInfo.batchSize > categories.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{categories.categoriesItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Cid"/>
                            </f:facet>
                            <h:outputText value="#{item.cid}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{categories.detailSetup}">
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][categories.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{categories.editSetup}">
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][categories.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{categories.remove}">
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][categories.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{categories.createSetup}" value="New Categories"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
