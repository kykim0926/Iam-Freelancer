var loginHdlr = {
	bIdValidateChk : false,  // 아이디 체크 여부
	bPwdValidateChk : false, // 비밀번호 패턴 체크 여부
	bPwdMatchValidateChk : false, // 비밀번호 확인 체크 여부
	bCertificateCompleteYn : false, // 핸드폰 인증 완료 여부
	validateClauseChk : function() {
		var bValid = true;
		
		if ($("input:checkbox[id='terms_agree']").is(':checked') == false) {
			alert("이용약관 동의를 체크해주세요.");
			bValid = false;
		}
		
		if ($("input:checkbox[id='policy_agree']").is(':checked') == false) {
			alert("개인정보 수집 및 이용 동의를 체크해주세요.");
			bValid = false;
		}
		
		if (bValid == false) {
			return;
		} else {
			location.href = "joinDetailRegist";
		}
	},

	pwdInputValidChk : function(evt) {
		
		if ($('#login_id').val() != '') {
			// 한글 입력 여부 체크
			if (korChk($('#login_id').val()) == false) {
				$('.idValidChk').show();
			} else {
				$('.idValidChk').hide();
			}

			// 로그인 ID 중복 체크	
			var idExistChk = false;

			if (userVO != null) { // 내 정보 수정일 경우
				idExistChk = 'false';	
			} else { // 회원 가입일 경우
				idExistChk = memberHdlr.memberIdExistChk($('#login_id').val());
			}
			
			if (idExistChk == 'true') { // 중복
				this.bIdValidateChk = false;
				$('.idExistChk').show();					
			} else { // 중복아님
				this.bIdValidateChk = true;
				$('.idExistChk').hide();
			} 
		}
		
		// 비밀번호 및 비밀번호 확인란 체크
		if ($('.loginPwd').val() != '' && $('#login_pwd_confirm').val() != '') {
			if (inputValidationChk($('.loginPwd').val()) == false) {
				this.bPwdValidateChk = false;
				$('.validMsg').show();
			} else {
				$('.validMsg').hide();
				this.bPwdValidateChk = true; 
			}
			
			if ($('#login_pwd_confirm').val() != $('#login_pwd').val()) {
				this.bPwdMatchValidateChk = false;
				$('.pwdMatchChk').show();
			} else {
				this.bPwdMatchValidateChk = true;
				$('.pwdMatchChk').hide();
			}
		// 비밀번호 및 비밀번호 확인란 체크
		} else if ($('.loginPwd').val() != '' && $('#login_pwd_confirm').val() == '') {
			if (!$(evt.target).hasClass('loginPwd') || (evt.keyCode != undefined && evt.keyCode == 9 && $(evt.target).hasClass("loginPwd")) ) {
				if (inputValidationChk($('.loginPwd').val()) == false) {
					this.bPwdValidateChk = false; 
					$('.validMsg').show();
				} else {
					$('.validMsg').hide();
					this.bPwdValidateChk = true; 
				}	
			} else {
				if ($('.loginPwd').val() != '' && !$(evt.target).hasClass("loginPwd")) {
					this.bPwdMatchValidateChk = false; 
					$('.validMsg').show();
				} else {
					this.bPwdMatchValidateChk = true;	
					$('.validMsg').hide();				
				}	
			}
		// 비밀번호 확인란 체크
		} else if ($('#login_pwd_confirm').val() != '') {
			if ($('#login_pwd_confirm').val() != $('#login_pwd').val() ||
			    (evt.keyCode != undefined && evt.keyCode == 9 && $(evt.target).hasClass("pwdMatchChk"))) {
				$('.pwdMatchChk').show();
				this.bPwdMatchValidateChk = false;
			} else {
				this.bPwdMatchValidateChk = true;
				$('.pwdMatchChk').hide();				
			}
		} else {
			$('.validMsg').hide();
			$('.pwdMatchChk').hide();
		}
	},
	
	// 회원가입 항목 체크
	registValidateChk : function(evt) {
		loginHdlr.pwdInputValidChk(evt);
		
		var bRegInfoChk = false;
		var bRegPwdChk = false;
		
		bRegInfoChk = loginHdlr.memberInfoValidateChk();
		bRegPwdChk = loginHdlr.memberPwdValidateChk();

		if (bRegInfoChk && bRegPwdChk) {
			return true;			
		} else {
			return false;
		}
	},
	
	// 개인정보 수정 항목 체크
	modifyValidateChk : function() {
		var bModInfoChk = false;
		bModInfoChk = loginHdlr.memberInfoValidateChk();
		
		if (bModInfoChk) {
			return true;
		} else {
			return false;
		}
	},
	
	// 회원 정보 체크
	memberInfoValidateChk : function(evt) {
		loginHdlr.pwdInputValidChk(evt);
		
		if ($('#name').val() == '') {
			alert('이름을 입력해주세요.');
			return false;
		}
		
		if ($('#login_id').val() == '') {
			alert('아이디를 입력해주세요.');
			return false;
		} else {
			if (!this.bIdValidateChk) {
				alert('아이디를 확인해주세요.');
				return false;	
			}
		}
		if (!this.bCertificateCompleteYn) {
			alert('휴대폰 인증을 해주십시요.');
			return false;	
		}

		var email = $('#email_user').val() + $('#at').text() + $('#email_domain').val() + $('#search_email_domain').val();
		if (!emailChk(email)) {
			alert('이메일을 올바르게 입력해주세요.');
			return false;
		}
		
		return true;
	},
	
	// 회원 비밀번호 체크
	memberPwdValidateChk : function() {
		var bPwdResult = true;
		
		if ($('#login_pwd').val() == '') {
			alert('비밀번호를 입력해주세요.');
			bPwdResult = false;
		} else {
			if (!this.bPwdValidateChk) {
				alert('비밀번호가 올바르지 않습니다.');
				bPwdResult =false;	
			}
		}
		
		if ($('#login_pwd').val() == '') {
			alert('비밀번호를 확인해 주세요.');
			bPwdResult =false;
		} else {
			if (!this.bPwdMatchValidateChk) {
				alert('비밀번호가 일치하지 않습니다.');
				bPwdResult =false;	
			}
		}
		
		return bPwdResult;
	}, 
	
	// 아이디 찾기
	findByNameAndEmail : function() {
		var jsonData = {
			name : $('#name').val(),
			email: $('#email').val()
		}
	
		$.ajax({
			url: '/login/findByNameAndEmail',
			dataType: 'text',
			type: 'POST',
			contentType: 'application/json',
			async: false,
			data: JSON.stringify(jsonData),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
			},
			success: function(data) {
				console.log('[/login/findByNameAndEmail success] :: ', data);
				if (data == 'not Exsit User Email') {
					alert("사용자의 아이디가 존재하지 않습니다.");
				} else if (data == 'Mail Send Error') {
					alert("메일 전송에 실패하였습니다. 관리자에게 문의하세요.");
				} else {
					alert("메일이 발송되었습니다. 메일을 확인하세요.");
				}
			},
			fail: function(xhr,status,error) {
				console.log('[/login/findByNameAndEmail fail] ::: '+xhr.responseText);
				alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
			},
			error: function(xhr,status,error) {
				console.log('/login/findByNameAndEmail error] :::'+xhr.responseText);
				alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
			}
		});
	}
}

$(function() {
	// 이부분의 $(this)는 전체 DOM을 가져온다. 
	$('#all_agrees').on('change',function() {
		if ($("input:checkbox[id='all_agrees']").is(':checked') == true) {
			$("input[id$='_agree']").prop('checked', true);			
		} else {
			$("input[id$='_agree']").prop('checked', false);
		}
	});
	
	$('#secondJoinStepBtn').unbind().on('click', function(){
		loginHdlr.validateClauseChk();
	});
	
	// 동의 체크박스 이벤트
	$('input:checkbox[id$=_agree]').unbind().on('change', function() {
		if ($("input:checkbox[id$='_agree']:checked").length == 2) {
			$("input:checkbox[id='all_agrees']").prop('checked', true);
		}
		
		if ($("input:checkbox[id$='_agree']:checked").length < 2) {
			
			$("input:checkbox[id='all_agrees']").prop('checked', false);
		}	
	});
	
	// 회원가입
	$('#memberRegist').click(function(evt) {
		var bRegCheckComplete = loginHdlr.registValidateChk(evt);
		
		if (bRegCheckComplete) {
			// 나눠져 있는 정보를 조합
			$('#mobile_num').val($("#mobile_exchange_num option:selected").val() + '-' + $("#mobile_first_num").val() + '-' + $("#mobile_second_num").val()); // 핸드폰
			$('#phone_num').val($("#phone_exchange_num option:selected").val() + '-' + $("#phone_first_num").val() + '-' + $("#phone_second_num").val()); // 전화
			$('#email').val($('#email_user').val() + $('#at').text() + $('#email_domain').val() + $('#search_email_domain').val()); // 이메일
//			$('#addr').val($('#addr_1').val() + $('#addr_2').val()); // 주소
			$('#user_type').val($('input:radio[name="user_type_group"]:checked').val()); // 구분
			$('#regist_route').val($('input:radio[name=regRoute_group]:checked').val()); // 가입경로

			var jsonData = {
				name: $('#name').val(),
				login_id: $('#login_id').val(),
				login_pwd: $('#login_pwd').val(),
				mobile_num: $('#mobile_num').val(),
				phone_num: $('#phone_num').val(),
				email: $('#email').val(),
				post_num: $('#post_num').val(),
				addr: $('#addr').val(),
				addr_detail: $('#addr_detail').val(),
				user_type: $('#user_type').val(),
				regist_route: $('#regist_route').val(),
				user_kind: $('#user_kind').val()
				, role_id: 'ROLE_MEMBER'
			};
			
			$.ajax({
				url: '/member/regist',
				dataType: 'text',
				type: 'POST',
				async: false,
				contentType: 'application/json',
				data: JSON.stringify(jsonData),
				beforeSend: function(xhr) {
					xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN)
				},
				success: function(data) {
					alert("성공적으로 가입되었습니다");
					$(location).attr('href', 'http://localhost:8080/pro/join3');
				},
				fail: function(error) {
					console.log("[fali]/member/regist", error);
					alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
					
				},
				error:function(xhr,status,error){
					console.log("[fali]/member/regist",xhr.responseText);
					alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
				}
			});
		}
	});
	
	// 회원정보 수정
	$('#memberInfoModify').click(function() {
		var bModCheckComplete = loginHdlr.modifyValidateChk();
		
		if (bModCheckComplete) {
			var jsonData = {
				name: $('#name').val(),
				login_id: $('#login_id').val(),
				mobile_num: $("#mobile_exchange_num option:selected").val() + '-' + $("#mobile_first_num").val() + '-' + $("#mobile_second_num").val(),
				phone_num: $("#phone_exchange_num option:selected").val() + '-' + $("#phone_first_num").val() + '-' + $("#phone_second_num").val(),
				email: $('#email_user').val() + $('#at').text() + $('#email_domain').val() + $('#search_email_domain').val(),
				post_num: $('#post_num').val(),
				addr: $('#addr').val(),
				addr_detail: $('#addr_detail').val(),
				user_type: $('input:radio[name="user_type_group"]:checked').val()
			};
			
			$.ajax({
				url: '/member/memberInfoUpdate',
				dataType:'text',
				type: 'POST',
				asycn: false,
				contentType: 'application/json',
				data: JSON.stringify(jsonData),
				beforeSend: function(xhr) {
					xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
				},
				success: function(data) {
					alert("성공적으로 수정되었습니다.");
				},
				fail: function(error) {
					console.log('[fail]/member/memberInfoUpdate ::: ', error);
					alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
				},
				error: function(xhr, status, error) {
					console.log("[error]/member/memberInfoUpdate ::: ",xhr.responseText);
					alert("오류가 발생하였습니다. 관리자에게 문의하세요.");
				}
			});
			
		}
	})
	
	// 회원 비밀번호 수정
	$('#memberPwdModify').click(function(evt) {
		loginHdlr.pwdInputValidChk(evt);
		var bModPwdChk = loginHdlr.memberPwdValidateChk();
		
		if (bModPwdChk) {
			var jsonData = {
				login_id: userVO.login_id,
				cur_login_pwd: $('#cur_login_pwd').val(),
				login_pwd: $('#login_pwd').val()
			}
			
			$.ajax({
				url: '/member/memberLoginPwdUpdate',
				dataType: 'text',
				type: 'POST',
				async: false,
				contentType: 'application/json',
				data: JSON.stringify(jsonData),
				beforeSend: function(xhr) {
					xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
				},
				success: function(data) {
					console.log("비밀전호 수정 : ", data);
					if(data == 'inconsistent' ){
						alert("비밀번호가 일치하지 않습니다.");
					} else {
						alert("성공적으로 수정되었습니다.");						
					}
				},
				fail: function(error) {
					alert("오류가 발생하였습니다. 관리자에게 문의하세요1.");
					console.log('[fail]/member/memberLoginPwdUpdate ::: ', error);
				},
				error: function(xhr, status, error) {
					alert("오류가 발생하였습니다. 관리자에게 문의하세요2.");
					console.log('[error]/member/memberLoginPwdUpdate ::: ', xhr.responseText)
				}
			})
		}
	});
	
	// 로그인 처리
	$('#userLogin').click(function() {
		var jsonData = {
			username: $('#input_login_id').val(),
			password: $('#input_login_pwd').val()	
		};
		
		/* 아래의 contentType과 data가 다른 ajax호출시와는 다르다.
		   SpringSecurity(loadUserByUsername)를 처리하기 위해서는 아래와 같이 한다. 
		*/
		$.ajax({
			url: '/pro/login',
			dataType:'json',
			type:'POST',
			async:false,
			contentType:'application/x-www-form-urlencoded',
			data: jsonData,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN)
			},
			success: function(data) {
				
			},
			fail: function() {
				
			},
			error:function(xhr,status,error){
				console.log('xhr:'+xhr.responseText);
			}
		});
	});
	
	// 이메일 도메인 콤보박스 선택
	$('.select-items').on('click', function() {
		if ($("#search_email_domain option:selected").val() == '') {
			$('#email_domain').attr('disabled', false); // 직접입력란 활성화
			$('#email_domain').css('background', '#f5f5f8');			
		} else {
			$('#email_domain').attr('disabled', true); // 직접입력란 비활성화
			$('#email_domain').css('background', '#a9a9c1');
			$('#email_domain').val(''); // 직접입력란 비활성화
		}
	});
	
		
	// 휴대폰 인증
	$('#mobileCert').click(function() {
		if(loginHdlr.bCertificateCompleteYn) {
			alert('이미 인증처리 완료되었습니다.');
			return;
		}
		
		if ($('#mobile_first_num').val() == '' || $('#mobile_second_num').val() == '') {
			alert('휴대폰 번호를 올바르게 입력해주세요.');
			return;
		}
		
		var mobileNum = $("#mobile_exchange_num option:selected").val() + $("#mobile_first_num").val() + $("#mobile_second_num").val();
		sendSMS(mobileNum);
	});
	
	// 휴대폰 인증 완료
	$('#mobileCertComplete').unbind().on('click', function() {
		var mobileNum = $("#mobile_exchange_num option:selected").val() + $("#mobile_first_num").val() + $("#mobile_second_num").val();
		mobileCerNumConfirm(mobileNum);
	});
	
	// 비밀번호 입력란에 입력중
	$('#login_pwd').keyup(function() {
		$('.validMsg').hide();
	});
	
	// 비밀번호 확인란에 입력중
	$('#login_pwd_confirm').keyup(function() {
		$('.pwdMatchChk').hide();
	});
	
	// 비밀번호 패턴 체크 : 회원가입상세 화면과 개인정수정 화면에서만 동작
	if (window.location.href.indexOf('joinDetailRegist') > -1 || $('#modMemberPage').length > 0) {
		$('html').click(function(evt) {
			loginHdlr.pwdInputValidChk(evt);			
		});	
	}
	
	// form 태그안의 input박스의 엔터키 이벤트 제거
	$("input:text, input:password").keydown(function(evt) {
		if (evt.keyCode == 13) {
			return;
		} else if (evt.keyCode == 9 && $(this).hasClass('regNecessary')) { // 아이디 및 비밀번호 입력시 탭 이벤트 체크
			loginHdlr.pwdInputValidChk(evt);
		}
	});
	
	// 주소검색
	$('#addrSearch').click(function() {
		fnZipCode();
	});
	
	// 전문가 or 의뢰읜 체크
	$('.user_kind').click(function() {
		if ($(this).hasClass('C')) {
			$('#user_kind').val('C');
		}else {
			$('#user_kind').val('S');
		}
	});
	
	// 아이디 찾기
	$('#findId').unbind().on('click', function(){
		loginHdlr.findByNameAndEmail();			
	});
});	