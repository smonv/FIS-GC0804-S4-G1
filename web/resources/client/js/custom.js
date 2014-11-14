function filter_product(url, mode) {

    $("#filter_button").click(function (event) {
        event.preventDefault();

        var filter_item = {};
        var filter_category = $("#filter_category").find(":selected").val();
        var filter_category_text = $("#filter_category").find(":selected").text();
        var filter_load = $("#filter_load").find(":selected").val();
        var filter_manufacturer = $("#filter_manufacturer").find(":selected").val();
        var filter_manufacturer_text = $("#filter_manufacturer").find(":selected").text();
        //////////////
        if (filter_category != 0) {
            if (!$.isNumeric(filter_category)) {
                alert("cid is number!");
                return;
            }
            filter_item["cid"] = filter_category;
            filter_item["category"] = filter_category_text;
        }

        if (filter_load != 0) {
            var loads = filter_load.split("-");
            if (!$.isNumeric(loads[0])) {
                alert("min load is number!");
                return;
            }

            if (!$.isNumeric(loads[1])) {
                alert("max load is number!");
                return;
            }

            filter_item["minLoad"] = loads[0];
            filter_item["maxLoad"] = loads[1];
        }
        
        if(filter_manufacturer != 0){
            if(!$.isNumeric(filter_manufacturer)){
                alert("nid is number!")
            }
            filter_item["mid"] = filter_manufacturer;
            filter_item["manufacturer"] = filter_manufacturer_text;
        }
        
        if(mode){
            filter_item["mode"] = mode;
        }

        //////////////

        if (Object.keys(filter_item).length > 0) {
            url += "?";
            var first_time = true;
            $.each(filter_item, function (key, value) {
                if (first_time == true) {
                    url += "" + key + "=" + value;
                    first_time = false;
                } else {
                    url += "&" + key + "=" + value;
                }
            });
        }

        //alert(url); 
        window.location.href = url;
    });
    

    $("#filter_clear").click(function (event) {
        event.preventDefault();
        window.location.href = url;
    });
}


function GetURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}

