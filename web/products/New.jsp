<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Products</title>
            <link rel="stylesheet" type="text/css" href="/FIS-GC0804-S4-G1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Products</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{products.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Code:"/>
                    <h:inputText id="code" value="#{products.products.code}" title="Code" required="true" requiredMessage="The code field is required." />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{products.products.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Infomations:"/>
                    <h:inputTextarea rows="4" cols="30" id="infomations" value="#{products.products.infomations}" title="Infomations" />
                    <h:outputText value="Price:"/>
                    <h:inputText id="price" value="#{products.products.price}" title="Price" />
                    <h:outputText value="ImagePath:"/>
                    <h:inputText id="imagePath" value="#{products.products.imagePath}" title="ImagePath" />
                    <h:outputText value="CreateAt (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="createAt" value="#{products.products.createAt}" title="CreateAt" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="OrderProductDetailsList:"/>
                    <h:selectManyListbox id="orderProductDetailsList" value="#{products.products.jsfcrud_transform[jsfcrud_class['control.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['control.util.JsfUtil'].jsfcrud_method.arrayToList].orderProductDetailsList}" title="OrderProductDetailsList" size="6" converter="#{orderProductDetails.converter}" >
                        <f:selectItems value="#{orderProductDetails.orderProductDetailsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="Cid:"/>
                    <h:selectOneMenu id="cid" value="#{products.products.cid}" title="Cid" >
                        <f:selectItems value="#{categories.categoriesItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{products.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{products.listSetup}" value="Show All Products Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
