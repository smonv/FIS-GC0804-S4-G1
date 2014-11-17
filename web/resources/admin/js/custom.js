function newProjectDatePicker() {
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    var start_at = $(".start_at").datepicker({
        format: 'dd/mm/yyyy',
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        if (ev.date.valueOf() > end_at.date.valueOf()) {
            var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate() + 1);
            end_at.setValue(newDate);
        }
        start_at.hide();
        $('.end_at')[0].focus();
    }).data('datepicker');
    ;
    var end_at = $(".end_at").datepicker({
        format: 'dd/mm/yyyy',
        onRender: function (date) {
            return date.valueOf() <= start_at.date.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        end_at.hide();
    }).data('datepicker');
}

function adminProjectIndexDatePicker() {
    $(".filter_start_at").datepicker({
        format: 'dd/mm/yyyy',
    });
    $(".filter_end_at").datepicker({
        format: 'dd/mm/yyyy',
    });
}

function createNewUrlAndRedirect(url, filter_item) {
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

