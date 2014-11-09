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

