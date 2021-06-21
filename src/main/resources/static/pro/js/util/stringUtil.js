//숫자만 입력(무제한(-1)을 위한 '-'도 입력 가능)
function numChk(n) {
	var regPattern1 = /[^0-9-]/g;
	var nData = n.value;
	if (regPattern1.test(nData)) {
		n.value = nData.replace(regPattern1, '');
	}
}

//영어 체크
function engChk(val) { 
	var regPattern1 = /[^A-Za-z]/g;
	var regPattern2 = /[^A-Z]/g;
	
	var vData = val.value;
	
	if (regPattern1.test(vData)) {
		val.value = vData.replace(regPattern1, '');
	} else if (regPattern2.test(vData)) {
		val.value = vData.toUpperCase();
	}
}

// 입력된 값 룰 체크 : 영어, 숫자, 특수문자 포함
function inputValidationChk(input) {
	if (input.split(' ').length > 1) { // 공백 체크
		return false;
	}

    var patternNum = /[0-9]/;
    var patternAlphabe = /[a-zA-Z]/;
    var patternSpecialChar = /[~!@#$%^&*()_+|<>?:{}]/;

    if (patternNum.test(input) && patternAlphabe.test(input) && patternSpecialChar.test(input)) {
    	return true;
    } else {
    	return false;
    }
}

// 이메일 형식 체크
function emailChk(email) {
     var pattern = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

     if (!pattern.test(email)) {                            
          return false;         
     } else {                       
          return true;         
     }                            
}

// 한글 입력 체크
function korChk(input) {
	var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    if (check.test(input)) {
		return false;
	} else {
		return true;		
	}
}