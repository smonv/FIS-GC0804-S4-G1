function filter_product(url) {
    
    $("#filter_button").click(function (event) {
        event.preventDefault();

        var filter_item = {};
        var filter_category = $("#filter_category").find(":selected").val();
        var filter_category_text = $("#filter_category").find(":selected").text();
        if (filter_category != 0) {
            filter_item["cid"] = filter_category;
            filter_item["category"] = filter_category_text;
        }
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
        
        window.location.href = url;
    });
    $("#filter_clear").click(function (event) {
        event.preventDefault();
        window.location.href = url;
    });
}

