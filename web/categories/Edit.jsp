<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Categories</title>
            <link rel="stylesheet" type="text/css" href="/FIS-GC0804-S4-G1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Categories</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Cid:"/>
                    <h:outputText value="#{categories.categories.cid}" title="Cid" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{categories.categories.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="ProductsList:"/>
                    <h:selectManyListbox id="productsList" value="#{categories.categories.jsfcrud_transform[jsfcrud_class['control.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['control.util.JsfUtil'].jsfcrud_method.arrayToList].productsList}" title="ProductsList" size="6" converter="#{products.converter}" >
                        <f:selectItems value="#{products.productsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{categories.edit}" value="Save">
                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{categories.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentCategories" value="#{jsfcrud_class['control.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][categories.categories][categories.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{categories.listSetup}" value="Show All Categories Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
