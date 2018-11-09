pagecontrollers['crud-controller'] = pagecontrollers['s-mailing-mails-controller'] = (function() {

    function SMailinMailsController() {

    }

    SMailinMailsController.prototype = new CrudController();

    SMailinMailsController.prototype.init = function (element) {
        console.log("SMailinMailsController initialized");
        CrudController.prototype.init.call(this, element);

        $("._datetimepicker").datetimepicker({
            format: "Y-m-d H:i"
        });

        // *** Submit schedule item ***
        $(this.element).find(".section.schedule-item .submit-item").click(this.onSubmitScheduleItem.bind(this))

        // *** Schedule Item ***
        $(this.element).find(".itemtable").on("click",".schedule-item",this.onScheduleItem.bind(this))


    }
    SMailinMailsController.prototype.onScheduleItem = function(event) {
        var target = event.currentTarget;
        var id = target.dataset["id"];
        var form = $(this.element).find(".schedule-item .item-form")[0];
        $(this.element).find(".create-item").addClass("hidden");
        $(this.element).find(".edit-item").addClass("hidden");
        form.children["id"].setAttribute("value",id);
        $(this.element).find(".schedule-item").removeClass("hidden");
    }

    SMailinMailsController.prototype.onSubmitScheduleItem = function(event) {
        var form = $(this.element).find(".section.schedule-item .item-form")[0];
        var item = this.convertFormToItem(form);
        this.ajax(this.resource+"/"+item.id+"/schedule","POST",item,function(data) {
            this.emptyForm(form);
            $(this.element).find(".section.schedule-item").addClass("hidden");
        });
    }

    return new SMailinMailsController();

}());