<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Products Detail</title>
            <link rel="stylesheet" type="text/css" href="/FIS-GC0804-S4-G1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Products Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pid:"/>
                    <h:outputText value="#{products.products.pid}" title="Pid" />
                    <h:outputText value="Code:"/>
                    <h:outputText value="#{products.products.code}" title="Code" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{products.products.name}" title="Name" />
                    <h:outputText value="Infomations:"/>
                    <h:outputText value="#{products.products.infomations}" title="Infomations" />
                    <h:outputText value="Price:"/>
                    <h:outputText value="#{products.products.price}" title="Price" />
                    <h:outputText value="ImagePath:"/>
                    <h:outputText value="#{products.products.imagePath}" title="ImagePath" />
                    <h:outputText value="CreateAt:"/>
                    <h:outputText value="#{products.products.createAt}" title="CreateAt" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="Cid:"/>
                    <h:panelGroup>
                        <h:outputText value="#{products.products.cid}"/>
                        <h:panelGroup rendered="#{products.products.cid != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{categories.detailSetup}">
                                <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products.cid][categories.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="products"/>
                                <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{categories.editSetup}">
                                <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products.cid][categories.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="products"/>
                                <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{categories.destroy}">
                                <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products.cid][categories.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="products"/>
                                <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>

                    <h:outputText value="OrderProductDetailsList:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty products.products.orderProductDetailsList}" value="(No Items)"/>
                        <h:dataTable value="#{products.products.orderProductDetailsList}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty products.products.orderProductDetailsList}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Opdid"/>
                                </f:facet>
                                <h:outputText value="#{item.opdid}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Quantity"/>
                                </f:facet>
                                <h:outputText value="#{item.quantity}"/>
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
                                    <h:outputText value="OrderId"/>
                                </f:facet>
                                <h:outputText value="#{item.orderId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ProductId"/>
                                </f:facet>
                                <h:outputText value="#{item.productId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{orderProductDetails.detailSetup}">
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentOrderProductDetails" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][orderProductDetails.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="products" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{orderProductDetails.editSetup}">
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentOrderProductDetails" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][orderProductDetails.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="products" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{orderProductDetails.destroy}">
                                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentOrderProductDetails" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][orderProductDetails.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="products" />
                                    <f:param name="jsfcrud.relatedControllerType" value="control.ProductsController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{products.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{products.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentProducts" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][products.products][products.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{products.createSetup}" value="New Products" />
                <br />
                <h:commandLink action="#{products.listSetup}" value="Show All Products Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
