$(window).scroll(function(){
  var y = $(this).scrollTop();
		if(y > 20){
			$(".header").slideDown(100);
		}	else{
			$(".header").slideUp(100);
		}
})
