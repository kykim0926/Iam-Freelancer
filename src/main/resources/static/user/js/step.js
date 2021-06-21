var vm = {
  name : ko.observable(""),
  email : ko.observable(""),
  phone : ko.observable(""),
  subject : ko.observable(""),
	step : ko.observable(1),
	prev : function(){
		var step = vm.step()-1;
	 	if(step >= 1){
	 		vm.step(step);
	 		$('.progress-bar').animate({
	 			width: step * 20 + '%'
	 		});
	 	}
	},
	next : function(){
		var step = vm.step()+1;
    if(step <= 6){
	 		vm.step(step);
	 		$('.progress-bar').animate({
	 			width: step * 20 + '%'
	 		});
	 	}
	}
}

ko.bindingHandlers.fadeVisible = {
    init: function(element, valueAccessor) {
        // Initially set the element to be instantly visible/hidden depending on the value
        var value = valueAccessor();
        $(element).toggle(ko.unwrap(value)); // Use "unwrapObservable" so we can handle values that may or may not be observable
    },
    update: function(element, valueAccessor) {
        // Whenever the value subsequently changes, slowly fade the element in or out
        var value = valueAccessor();
        ko.unwrap(value) ? $(element).fadeIn() : $(element).hide();
    }
};


ko.applyBindings(vm);