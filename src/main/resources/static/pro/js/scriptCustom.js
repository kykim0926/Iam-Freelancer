/* 셀렉트 */
var x, i, j, l, ll, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("custom-select");
l = x.length;
for (i = 0; i < l; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  ll = selElmnt.length;

//userVO.phone_num.substring(0, userVO.phone_num.indexOf('-'))
  /*for each element, create a new DIV that will act as the selected item:*/

  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;

  x[i].appendChild(a);
  /*for each element, create a new DIV that will contain the option list:*/
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < ll; j++) {
    /*for each option in the original select element,
    create a new DIV that will act as an option item:*/
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var y, i, k, s, h, sl, yl;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        sl = s.length;
        h = this.parentNode.previousSibling;
        for (i = 0; i < sl; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            yl = y.length;
            for (k = 0; k < yl; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes,
      and open/close the current select box:*/
      e.stopPropagation();
      closeAllSelect(this);
      this.nextSibling.classList.toggle("select-hide");
      this.classList.toggle("select-arrow-active");
    });
}
function closeAllSelect(elmnt) {
  /*a function that will close all select boxes in the document,
  except the current select box:*/
  var x, y, i, xl, yl, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  xl = x.length;
  yl = y.length;
  for (i = 0; i < yl; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < xl; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);


/*input-날짜*/
//$(function () {
//  $("#datepicker").datepicker();
//  $("#datepicker_init_day").datepicker();
//  $("#datepicker_init_day").datepicker('setDate', new Date);
//
//  $("#datepicker_special_day").datepicker();
//  $("#datepicker_special_day").datepicker('setDate', '2018-12-25');
//
//  var today = new Date(); // 오늘날짜가 만들어진다.
//  console.log('오늘 날짜  => ' + today);
//  today.setDate(today.getDate() + 3); // 3일을 더하기
//  console.log('3일 후 날짜  => ' + today);
//
//  $("#datepicker_add_day").datepicker();
//  $("#datepicker_add_day").datepicker('setDate', today);
//});

/*더보기 */
$(function() {
  $(".service_list")
    .slice(0, 3)
    .show();
  $("#load-more").on("click", function(e) {
    e.preventDefault();
    $(".service_list:hidden")
      .slice(0, 3)
      .slideDown();
    if ($(".service_list:hidden").length == 0) {
      $("#load-more").fadeOut("slow");
    }
    $("html,body").animate(
      {
        scrollTop: $(this).offset().top
      },
      1500
    );
  });
});


/*메뉴 active */
$(document).ready(function(){
  $('.tab_menu li a').click(function(){
    $('.tab_menu li a').removeClass("active");
    $(this).addClass("active"); 
});

$(document).ready(function() {

	$(".tab_step1 li").click(function() {
		var idx = $(this).index();
		$(".tab_step2").hide();
		$(".tab_step1 li").removeClass("on");
		$(this).addClass("on");
		var $snav = $(".tab_step2.s" + (idx + 1));
		$snav.css('display', 'flex');
		$snav.find("div").eq(0).trigger("click");
	});

	tabInit(0);
});

function tabInit(index) {
	$(".tab_step1 li").eq(index).trigger("click");
}


/* bx슬라이더 */
//$('.bxslider').bxSlider({
//  mode: 'fade',
//  auto: true,
//  autoControls: false, //시작멈춤버튼
//  stopAutoOnClick: true,
//  pager: false,
//  controls: true
//  });
//
//  $('.bxslider2').bxSlider( {
//    mode: 'horizontal',// 가로 방향 수평 슬라이드
//    speed: 1000,        // 이동 속도를 설정
//    pager: false,      // 현재 위치 페이징 표시 여부 설정 - 땡땡이
//    moveSlides: 1,     // 슬라이드 이동시 개수
//    slideWidth: 260,   // 슬라이드 너비
//    slideHeight: 380,   // 슬라이드 높이
//    minSlides: 10,      // 최소 노출 개수
//    maxSlides: 10,      // 최대 노출 개수
//    auto: true,        // 자동 실행 여부
//    autoHover: true,   // 마우스 호버시 정지 여부
//    controls: false,    // 이전 다음 버튼 노출 여부 - < > 좌우버튼
//    infiniteLoop:true  //무한루프        
//});
//
//$('.bxslider3').bxSlider( {
//  mode: 'horizontal',// 가로 방향 수평 슬라이드
//  auto: false,
//  autoControls: false,//시작멈춤버튼
//  stopAutoOnClick: false,
//  pager: true,
//  controls: false,
//  slideWidth: 600,
//  pagerCustom : '#bx-pager' //썸네일
//});
//
//$("#bx-pager").bxSlider({
//  slideWidth: 275,
//  minSlides: 3,
//  maxSlides: 3,
//  slideMargin: 0,
//  controls: true,
//  pager: false,
//  infiniteLoop: true,
//  prevText: "",
//  nextText: ""
//}); 
//
//$('.bxslider4').bxSlider( {
//  mode: 'horizontal',// 가로 방향 수평 슬라이드
//  speed: 1000,        // 이동 속도를 설정
//  pager: false,      // 현재 위치 페이징 표시 여부 설정 - 땡땡이
//  moveSlides: 4,     // 슬라이드 이동시 개수
//  slideWidth: 200,   // 슬라이드 너비
//  slideHeight: 200,   // 슬라이드 높이
//  minSlides: 10,      // 최소 노출 개수
//  maxSlides: 10,      // 최대 노출 개수
//  auto: true,        // 자동 실행 여부
//  autoHover: true,   // 마우스 호버시 정지 여부
//  controls: true,    // 이전 다음 버튼 노출 여부 - < > 좌우버튼
//  infiniteLoop:true  //무한루프        
//});


$('ul.tabs li').click(function(){
  var tab_id = $(this).attr('data-tab');

  $('ul.tabs li').removeClass('current');
  $('.tab-content').removeClass('current');

  $(this).addClass('current');
  $("#"+tab_id).addClass('current');
});

});



/* 탭메뉴1 */

// Init
$('.tab_line').append('<div class="tab_line-el"></div>').addClass('tab_line-ready');

$('.tab_line a').click(function() {
  menuLine($(this));
});

function menuLine($el) {
  var $menu = $el.closest('.tab_line');
  var $menuEl = $menu.find('.tab_line-el');
  
  var width = $el.width();
  var offset = $el.position();
  
  // $menuEl.css({
  //   left: offset.left + 'px',
  //   width: width + 'px'
  // });
}

function menuLineOut($menu) {
  var $menuEl = $menu.find('.tab_line-el');  
  $menuEl.css('width', '');
}
menuLine($('.tab_menu._1 a:first'));

// Other
$('.tab_menu._1 a').click(function(e) {
  e.preventDefault();
});

/* 탭메뉴1 - 끝 */

$(".like").click(function(){  
  if($(this).hasClass('fill') == false){
    $(this).addClass('fill');
  }else{
    $(this).removeClass('fill');
  }
});


$(".tag3").click(function(){  
  if($(this).hasClass('choice') == false){
    $(this).addClass('choice');
  }else{
    $(this).removeClass('choice');
  }
});


/* 파일찾기 */
$('#chooseFile').bind('change', function () {
  var filename = $("#chooseFile").val();
  if (/^\s*$/.test(filename)) {
    $(".file-upload").removeClass('active');
    $("#noFile").text("No file chosen..."); 
  }
  else {
    $(".file-upload").addClass('active');
    $("#noFile").text(filename.replace("C:\\fakepath\\", "")); 
  }
});



/*모달*/
$(".modal_btn").on("click", function() {
  var modal = $(this).data("modal");
  $(modal).show();
});

$(".modal").on("click", function(e) {
  var className = e.target.className;
  if(className === "closeBtn" || className === "btn_close"){
    $(this).closest(".modal").hide();
  }
});


// 파일업로드
$(function() {
  var countFiles = 1,
    $body = $('body'),
    typeFileArea = ['txt', 'doc', 'docx', 'ods'],
    coutnTypeFiles = typeFileArea.length;

  //create new element
  $body.on('click', '.files-wr label', function() {
    var wrapFiles = $('.files-wr'),
      newFileInput;

    countFiles = wrapFiles.data('count-files') + 1;
    wrapFiles.data('count-files', countFiles);

    newFileInput = '<div class="one-file"><label for="file-' + countFiles + '">+ 파일추가</label>' +
      '<input type="file" name="file-' + countFiles + '" id="file-' + countFiles + '"><div class="file-item hide-btn">' +
      '<span class="file-name"></span><span class="btn btn-del-file">x</span></div></div>';
    wrapFiles.prepend(newFileInput);
  });

  //show text file and check type file
  $body.on('change', 'input[type="file"]', function() {
    var $this = $(this),
      valText = $this.val(),
      fileName = valText.split(/(\\|\/)/g).pop(),
      fileItem = $this.siblings('.file-item'),
      beginSlice = fileName.lastIndexOf('.') + 1,
      typeFile = fileName.slice(beginSlice);

    fileItem.find('.file-name').text(fileName);
    if (valText != '') {
      fileItem.removeClass('hide-btn');

      for (var i = 0; i < coutnTypeFiles; i++) {

        if (typeFile == typeFileArea[i]) {
          $this.parent().addClass('has-mach');
        }
      }
    } else {
      fileItem.addClass('hide-btn');
    }

    if (!$this.parent().hasClass('has-mach')) {
      $this.parent().addClass('error');
    }
  });

  //remove file
  $body.on('click', '.btn-del-file', function() {
    var elem = $(this).closest('.one-file');
    elem.fadeOut(400);
    setTimeout(function() {
      elem.remove();
    }, 400);
  });
});

$.fn.ashCordian = function() {  
  var container = $(this);
  container.find('div').click(function() {
    if($(this).siblings('section').css('display') == 'block'){
       container.find('section').slideUp(150);
    } else {
      container.find('section').slideUp(150);
       $(this).siblings('section').slideDown(150);
    }
  });
};

$('#accord1').ashCordian();
$('#accord2').ashCordian();
$('#accord3').ashCordian();

// 메인 검색창
$(document).on('ready', function() {
  
  $('.field').on('focus', function() {
    $('body').addClass('is-focus');
  });
  
  $('.field').on('blur', function() {
    $('body').removeClass('is-focus is-type');
  });
  
  $('.field').on('keydown', function(event) {
    $('body').addClass('is-type');
    if((event.which === 8) && $(this).val() === '') {
      $('body').removeClass('is-type');
    }
  });
  
});

// 이미지파일 첨부
$('input[type="file"]').each(function(){
  // Refs
  var $file = $(this),
      $label = $file.next('label'),
      $labelText = $label.find('span'),
      labelDefault = $labelText.text();
  
  // When a new file is selected
  $file.on('change', function(event){
    var fileName = $file.val().split( '\\' ).pop(),
        tmppath = URL.createObjectURL(event.target.files[0]);
    //Check successfully selection
		if( fileName ){
      $label
        .addClass('file-ok')
        .css('background-image', 'url(' + tmppath + ')');
			$labelText.text(fileName);
    }else{
      $label.removeClass('file-ok');
			$labelText.text(labelDefault);
    }
  });
  
// End loop of file input elements  
});