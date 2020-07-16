<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		$('#btnSearchVoucher').click(function() {
			var url="voucherSearchPopup.ajax";
			
			var width = 800;
			var height = 500;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});

		$('#btnSearchCustPop').click(function() {

			var rchrtTp = $('#rchrgTpPop').val();

			var url="customerSearchPopup.ajax?popupType=o&rchrgTp=" + rchrtTp;
			
			var width = 900;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});

		$('#btnSearchCtrtPop').click(function() {

			var custId = $('#tgtCustIdPop').val();
			var custNm = $('#tgtCustNmPop').val();
			var soId = $('#tgtCustSoIdPop').val();
			var soNm = $('#tgtCustSoNmPop').val();

			if (custId == null || custId == '') {
				alert('<spring:message code="MSG.M03.MSG00013" />');
				return;
			}

			var url = "chrgCtrtListPopup.ajax";
			url = url + "?custId=" + custId;
			url = url + "&custNm=" + custNm;
			url = url + "&soId=" + soId;
			url = url + "&soNm=" + soNm;

			var width = 800;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});

		$('#btnSearchProdPop').click(function() {
			// Holiday Pack 추가로 상품가져올때 Holiday Pack 구분값 세팅
			var preBasicProdRemainYn = $('#preBasicProdYnInfoRemainYn').val();
			var preBasicProdPcrfYn = $('#preBasicProdYnInfoPcrfYn').val();
			var preBasicProdPcrfId = $('#preBasicProdYnInfoPcrfId').val();
			var preBasicProdLiteYn = $('#preBasicProdYnInfoLiteYn').val();
			var preBasicProdDiscYn = $('#preBasicProdYnInfoDiscYn').val();

			var bundleRemainYn = $('#preBundleProdYnInfoRemainYn').val();
			var bundlePcrfYn = $('#preBundleProdYnInfoPcrfYn').val();
			var bundleLiteYn = $('#preBundleProdYnInfoLiteYn').val();
			
			// Holiday Pack 구분
			// 공통코드 PP00319 : Holiday Promo Type
			var holiTp = '';
			
			// Discount 속성이 C or D 인 경우 지정된 Holiday Pack 세팅
			if (preBasicProdDiscYn == "C" || preBasicProdDiscYn == "D") {
				// 기본 DISC_YN = "C", PCRF_YN = "Y", LITE_YN = "Y" : SpeedYearly 전용 Holiday Pack
				if (preBasicProdDiscYn == "C" && preBasicProdPcrfYn == "Y" && preBasicProdLiteYn == "Y") {
					holiTp = "PS";
				}
				// 기본 DISC_YN = "C", PCRF_YN = "N", LITE_YN = "Y" : SpeedYearly Hybrid 전용 Holiday Pack
				else if (preBasicProdDiscYn == "C" && preBasicProdPcrfYn == "N" && preBasicProdLiteYn == "Y") {
					holiTp = "HS";
				}
				// 기본 DISC_YN = "D", PCRF_YN = "Y", LITE_YN = "Y" : Small Volume 전용 Holiday Pack
				else if (preBasicProdDiscYn == "D" && preBasicProdPcrfYn == "Y" && preBasicProdLiteYn == "Y") {
					holiTp = "SV";
				}
			}
			// Discount 속성이 N 인 경우(일반 or Lite)
			else {
				// 기본 Y 인 경우
				if (preBasicProdRemainYn == "Y") {
					// 번들 N 인 경우 (기본만 유효한)
					if (bundleRemainYn == "N") {
						// 기본 PCRF_YN = "N", LITE_YN = "X" : 일반 전용 Holiday Pack
						if (preBasicProdPcrfYn == "N" && preBasicProdLiteYn == "X") {
							holiTp = "GE";
						}
						// 기본 PCRF_YN = "Y", LITE_YN = "Y" : Lite 전용 Holiday Pack
						else if (preBasicProdPcrfYn == "Y" && preBasicProdLiteYn == "Y") {
							holiTp = "LI";
						}
						// 기본 PCRF_YN = "Y", LITE_YN = "X" : UL Holiday Pack 세팅X
						else if (preBasicProdPcrfYn == "Y" && preBasicProdLiteYn == "X") {
							holiTp = "XX";
						}
						// 기본 PCRF_YN = "Y", LITE_YN = "N" : Lite UL Holiday Pack 세팅X
						else if (preBasicProdPcrfYn == "Y" && preBasicProdLiteYn == "N") {
							holiTp = "XX";
						}
					}
					// 번들 Y 인 경우 (기본 + 번들1 유효한)
					else {
						// 번들 PCRF_YN = "N", LITE_YN = "X" : 일반 전용 Holiday Pack
						if (bundlePcrfYn == "N" && bundleLiteYn == "X") {
							holiTp = "GE";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "Y" : Lite 전용 Holiday Pack
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "Y") {
							holiTp = "LI";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "X" : UL Holiday Pack 세팅X
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "X") {
							holiTp = "XX";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "N" : Lite UL Holiday Pack 세팅X
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "N") {
							holiTp = "XX";
						}
					}
				}
				// 기본 N 인 경우
				else {
					// 번들 N 인 경우
					if (bundleRemainYn == "N") {
						// 유효하지 않은 경우 Holiday Pack 세팅X
						holiTp = "XX";
					}
					// 번들 Y 인 경우 (번들1 유효한)
					else {
						// 번들 PCRF_YN = "N", LITE_YN = "X" : 일반 전용 Holiday Pack
						if (bundlePcrfYn == "N" && bundleLiteYn == "X") {
							holiTp = "GE";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "Y" : Lite 전용 Holiday Pack
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "Y") {
							holiTp = "LI";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "X" : UL Holiday Pack 세팅X
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "X") {
							holiTp = "XX";
						}
						// 번들 PCRF_YN = "Y", LITE_YN = "N" : Lite UL Holiday Pack 세팅X
						else if (bundlePcrfYn == "Y" && bundleLiteYn == "N") {
							holiTp = "XX";
						}
					}
				}
			}

			var soId = '${ctrtInfo.soId}';
			var soNm = '${ctrtInfo.soNm}';
			var ctrtId = '${ctrtInfo.ctrtId}';
			var userId = '${loginUser.userId}';
			var prodCd = $('#preBasicProdYnInfoProdCd').val();
			var discYn = preBasicProdDiscYn;
			var rchrgTermDt = $('#latestBundleProdInfoRchrgTermDt').val();
			var pcrfId = preBasicProdPcrfId;
			
			var url = "chrgProdPopup.ajax";
			url = url + '?soId=' + soId;
			url = url + '&soNm=' + soNm;
			url = url + '&ctrtId=' + ctrtId;
			url = url + '&userId=' + userId;
			url = url + '&prodCd=' + prodCd;
			url = url + '&discYn=' + discYn;
			url = url + '&rchrgTermDt=' + rchrgTermDt;
			url = url + '&pcrfId=' + pcrfId;
			url = url + '&popupType=o';

			var width = 800;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});

		$('#btnChargePop').click(function() {

			// 시작일자, 유효기간 분기 var
			// cmb_rchrgTp.Value == "500", "600" 인 경우 분기가 달라질 수 있음.
			// "700" 추가(상품바우쳐Top-up) : 600과 비슷한 케이스
			
			/* (1) calTyp = A (시작일자, 종료일자 적용없음)
			   1. 500/600 아닌 경우
			   
			   (2) calTyp = B (시작일자 : 현재기준, 종료일자 : 현재기준 + 유효기간)
			   1. 500/600 기본(일반/PCRF) 유효하지 않고, 번들구매 없음
			   2. 500/600 기본(일반/PCRF) 유효하지 않고, 번들일반구매 1건
			   3. 500/600 기본일반 유효하고, 번들구매 없음
			   4. 500/600 기본일반 유효하고, 번들일반구매 1건
			   
			   (3) calTyp = C (시작일자 : 기본PCRF종료일자 다음일, 종료일자 : 시작일자 + 유효기간) 예약
			   1. 500/600 기본PCRF 유효하고, 번들구매 없음
			   2. 500/600 기본PCRF 유효하고, 번들일반구매 1건
			   + K-Movie, Netflix 와 같이 공제 두개짜리 기본 : 기본 종료일자까지 예약(RG_PROD_TP : 0)
			   
			   (4) calTyp = D (시작일자 : 번들PCRF종료일자 다음일, 종료일자 : 시작일자 + 유효기간) 예약
			   1. 500/600 기본(일반/PCRF) 유효하지 않고, 번들PCRF구매 1건
			   2. 500/600 기본일반 유효하고, 번들PCRF구매 1건
			   + K-Movie, Netflix 와 같이 공제 두개짜리 번들 : 번들 종료일자까지 예약(RG_PROD_TP : 0)
			   
			   (5) calTyp = E (Lite/Lite UL 기본인 경우 번들구매시 유효기간에는 구매불가, Lite/Lite UL 번들구매시 이전상품 유효기간에는 구매불가)
			   
			   (6) calTyp = F (Lite 기본인 경우 번들구매시 PCRF_ID가 같은 경우 구매가능, 다른 경우 이전상품 유효기간에는 구매불가)
			   + K-Movie, Netflix 와 같이 공제 두개짜리 : 번들구매시 유효기간에 PCRF_ID 같은 경우 구매가능(RG_PROD_TP : 1)
			   
			   (7) calTyp = G K-Movie, Netflix 와 같이 공제 두개짜리 : 번들구매시 유효기간에 PCRF_ID 다른 경우 구매불가(RG_PROD_TP : 1)
			   
			   ※ Speed+Yearly 할인상품은 구매 가능 패턴은 calTyp = B
			   ※ Small bundle add-on 구매 가능 패턴은 calTyp = B
			*/

			// PV 응답없어 처리되지 않은 경우 이전 프로세스가 종료될 때 까지 이후 프로세스를 진행할 수 없음
			var notResPvCount = $('#notResPvCount').val();

			// if (notResPvCount == '1') {
			// 	return false;
			// }

			// 충전 유형
			var rchrgTp = $('#rchrgTpPop').val();
			var calTyp = '';

			// 잔액
			var rmnAmt = $('#rmnAmtPop').val();
			// 충전금액
			var chrgAmt = $('#rchrgAmtPop').val();
			// 바우처 관리번호
			var voBarCd = $('#voBarCdPop').val();
			// 선물대상 고객
			var custId = $('#tgtCustIdPop').val();
			var custNm = $('#tgtCustNmPop').val();
			// 대상 계약
			var ctrtId = $('#tgtCtrtIdPop').val();

			// 상품
			var prodSoId = $('#prodSoId').val();
			var prodDhType = $('#prodDhType').val();
			var prodPcrfId = $('#prodPcrfId').val();
			var prodLiteYn = $('#prodLiteYn').val();
			var prodPdvcYn = $('#prodPdvcYn').val();
			var prodDiscYn = $('#prodDiscYn').val();
			var prodHoliYn = $('#prodHoliYn').val();
			var prodRgProdTp = $('#prodRgProdTp').val();
			var prodDedtAmt = $('#prodDedtAmt').val();
			var prodDnType = $('#prodDnType').val();
			var prodSoNm = $('#prodSoNm').val();
			var prodProdCd = $('#prodProdCd').val();
			var prodProdNm = $('#prodProdNm').val();
			var prodProdAmt = $('#prodProdAmt').val();
			var prodQosAmt = $('#prodQosAmt').val();
			var prodEffPeriod = $('#prodEffPeriod').val();
			var prodPcrfYn = $('#prodPcrfYn').val();
			var prodRcmdYn = $('#prodRcmdYn').val();

			// 기본상품
			var basicProdGrp = '${ctrtInfo.basicProdGrp}';
			var preBasicRemainYn = $('#preBasicProdYnInfoRemainYn').val();
			var preBasicPcrfId = $('#preBasicProdYnInfoPcrfId').val();
			var preBasicPcrfYn = $('#preBasicProdYnInfoPcrfYn').val();
			var preBasicLiteYn = $('#preBasicProdYnInfoLiteYn').val();
			var preBasicDiscYn = $('#preBasicProdYnInfoDiscYn').val();
			var preBasicTermDt = $('#preBasicProdYnInfoTermDt').val();
			var preBasicRgProdTp = $('#preBasicProdYnInfoRgProdTp').val();
			var preBasicProdCd = $('#preBasicProdYnInfoProdCd').val();
			var preBasicDedtAmt = $('#preBasicProdYnInfoDedtAmt').val();

			// 번들상품
			var preBundleRemainYn = $('#preBundleProdYnInfoRemainYn').val();
			var preBundlePcrfId = $('#preBundleProdYnInfoPcrfId').val();
			var preBundlePcrfYn = $('#preBundleProdYnInfoPcrfYn').val();
			var preBundleLiteYn = $('#preBundleProdYnInfoLiteYn').val();
			var preBundleTermDt = $('#preBundleProdYnInfoTermDt').val();
			var preBundleRgProdTp = $('#preBundleProdYnInfoRgProdTp').val();

			// 최근구매 상품
			var latestBundleProdCd = $('#latestBundleProdInfoProdCd').val();
			var latestBundleDiscYn = $('#latestBundleProdInfoDiscYn').val();
			var latestBundleDedtAmt = $('#latestBundleProdInfoDedtAmt').val();
			var latestBundleRchrgTermDt = $('#latestBundleProdInfoRchrgTermDt').val();

			if (rchrgTp == '100') {
				// 데이터충전 (CASH)
				calTyp = 'A';

				if (chrgAmt == null || chrgAmt == '') {
					// 충전금액을 입력하세요.
					alert('<spring:message code="MSG.M10.MSG00027" />');
					return false;
				}
			} else if (rchrgTp == '300') {
				// 바우처충전
				calTyp = 'A';

				if (voBarCd == null || voBarCd == '') {
					alert('<spring:message code="MSG.M06.MSG00006" />');
					return false;
				}

				var todayMillis = new Date().getTime();
				var voucherExpiredMills = new Date($('#voucherVoExpiredDt').val()).getTime();

				if (todayMillis > voucherVoExpiredDt) {
					alert('<spring:message code="MSG.M05.MSG00001" />');
					return false;
				}
			} else if (rchrgTp == '400') {
				// 선물하기
				calTyp = "A";

				if (chrgAmt == null || chrgAmt == '') {
					// 충전금액을 입력하세요.
					alert('<spring:message code="MSG.M10.MSG00027" />');
					return false;
				}

				if (custNm == null || custNm == '') {
					// 대상고객을 입력하세요.
					alert('<spring:message code="MSG.M03.MSG00014" />');
					return false;
				}

				if (ctrtId == null || ctrtId == '') {
					// 대상계약ID를 입력하세요.
					alert('<spring:message code="MSG.M03.MSG00012" />');
					return false;
				}

				if (chrgAmt > rmnAmt) {
					// 충전금액은 잔액보다 작아야합니다.
					alert('<spring:message code="MSG.M10.MSG00026" />');
					return false;
				}

				if (chrgAmt <= 0) {
					// 충전금액은 0보다 커야합니다.
					alert('<spring:message code="MSG.M10.MSG00025" />');
					return false;
				}

			} else if (rchrgTp == '500' || rchrgTp == '600' || rchrgTp == '700') {
				// Bundle 상품구매
				// 분기 통합 (20160629)
				// 500 Bundle 상품구매
				// 600 데이터충전 (CASH) & Bundle 상품구매
				// 700 바우쳐충전 & Bundle 상품구매
				if (rchrgTp == '500') {

					if (prodProdCd == null || prodProdCd == '') {
						// 상품을 입력하세요.
						alert('<spring:message code="MSG.M07.MSG00066" />');
						return false;
					}

					if (chrgAmt > rmnAmt) {
						// 충전금액은 잔액보다 작아야합니다.
						alert('<spring:message code="MSG.M10.MSG00026" />');
						return false;
					}
				} else if (rchrgTp == '600') {
					if (prodProdCd == null || prodProdCd == '') {
						// 상품을 입력하세요.
						alert('<spring:message code="MSG.M07.MSG00066" />');
						return false;
					}
				} else if (rchrgTp == '700') {
					if (voBarCd == null || voBarCd == '') {
						alert('<spring:message code="MSG.M06.MSG00006" />');
						return false;
					}

					var todayMillis = new Date().getTime();
					var voucherExpiredMills = new Date($('#voucherVoExpiredDt').val()).getTime();

					if (todayMillis > voucherVoExpiredDt) {
						alert('<spring:message code="MSG.M05.MSG00001" />');
						return false;
					}
				}

				// 선불, 하이브리드
				if (basicProdGrp == "a" || basicProdGrp == "d") {
					// 기본상품 유효(기간중 or 잔여량있음)
					if (preBasicRemainYn == "Y") {
						// Speed+Yearly(DISC_YN = 'C')
						if (preBasicDiscYn == "C") {
							// 번들상품 1개 유효(잔여량 있음)
							if (preBundleRemainYn == "Y") {
								// 기본상품 공제량이 100 인 경우
								if (preBasicDedtAmt == "100") {
									// 최근 구매 번들이 공제량 100 인 경우
									if (latestBundleDedtAmt == "100") {
										// 최근 구매 번들이 Daily/Night 인 경우(DISC_YN = 'N' AND 공제량 100) 반드시 DISC_YN = 'Y' AND 공제량 100 인 번들 구매해야 함 
										if (latestBundleDiscYn == "N") {
											// 선택한 번들이 Daily/Night 인 경우(DISC_YN = 'N' AND 공제량 100) 구매불가
											if (prodDiscYn == "N" && prodDedtAmt == "100") {
												alert('<spring:message code="MSG.M10.MSG00012" />'); // 최근 구매한 Bundle 상품이 Daily/Night time Pack 입니다. Daily/Night time Pack은 이어서 구매할 수 없습니다.
												return false;
											}
											// 이외
											else {
												// 제한없이 구매 가능
												calTyp = "B";
											}
										}
										// 이외
										else {
											// 제한없이 구매 가능
											calTyp = "B";
										}
									}
									// 이외
									else {
										// 제한없이 구매 가능
										calTyp = "B";
									}
								}
								// 기본상품 공제량이 100 이 아닌 경우
								else {
									// 제한없이 구매 가능
									calTyp = "B";
								}
							}
							// 번들상품 2개 추가구매불가
							else if (preBundleRemainYn == "T") {
								alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
								return false;
							}
							// 번들구매없음
							else if (preBundleRemainYn == "N") {
								// 제한없이 구매 가능
								calTyp = "B";
							}
						}
						// Small bundle add-on(DISC_YN = 'D')
						else if (preBasicDiscYn == "D") {
							// 번들상품 1개 유효(잔여량 있음)
							if (preBundleRemainYn == "Y") {
								// 최근 구매 번들의 유효기간이 종료되지 않은 경우 할인적용, 유효기간 만료된 경우 할인미적용
								// 제한없이 구매 가능
								calTyp = "B";
							}
							// 번들상품 2개 추가구매불가
							else if (preBundleRemainYn == "T") {
								alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
								return false;
							}
							// 번들구매없음
							else if (preBundleRemainYn == "N") {
								// 제한없이 구매 가능
								calTyp = "B";
							}
						}
						// 기존
						else {
							// RG_PROD_TP : X (기존)
							if (preBasicRgProdTp == "X") {
								// 기본일반
								if (preBasicPcrfYn == "N" && preBasicLiteYn == "X") {
									// 번들상품 1개 유효(잔여량 있음)
									if (preBundleRemainYn == "Y") {
										// 번들일반
										if (preBundlePcrfYn == "N" && preBundleLiteYn == "X") {
											// 번들2 일반 바로 구매가능
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "B";
											}
											// 번들2 PCRF 바로 구매가능
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "B";
											}
											// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												calTyp = "E";
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들PCRF
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "X") {
											// 번들2 일반 번들1 종료까지 예약
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "D";
											}
											// 번들2 PCRF 번들1 종료까지 예약
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "D";
											}
											// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												calTyp = "E";
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들Lite
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "Y") {
											// 번들2 일반 번들1 유효(기간중 or 잔여량있음) 구매불가
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 PCRF 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 Lite
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
												if (preBundlePcrfId == prodPcrfId) {
													calTyp = "F";
												}
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
												else {
													calTyp = "E";
												}
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들Lite UL
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "N") {
											// 모든 경우 번들1 유효(기간중 or 잔여량있음) 구매불가
											calTyp = "E";
										}
									}
									// 번들상품 2개 추가구매불가
									else if (preBundleRemainYn == "T") {
										alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
										return false;
									}
									// 번들구매없음
									else if (preBundleRemainYn == "N") {
										// 번들일반 바로 구매가능
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "B";
										}
										// 번들PCRF 바로 구매가능
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "B";
										}
										// 번들Lite 기본상품 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											calTyp = "E";
										}
										// 번들Lite UL 기본상품 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
								}
								// 기본PCRF
								else if (preBasicPcrfYn == "Y" && preBasicLiteYn == "X") {
									// 번들상품 1개 유효(잔여량 있음)
									if (preBundleRemainYn == "Y") {
										// 번들일반
										if (preBundlePcrfYn == "N" && preBundleLiteYn == "X") {
											// 번들2 일반 기본종료까지 예약
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "C";
											}
											// 번들2 PCRF 기본종료까지 예약
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "C";
											}
											// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												calTyp = "E";
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들PCRF
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "X") {
											// 번들2 일반 번들1 종료까지 예약
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "D";
											}
											// 번들2 PCRF 번들1 종료까지 예약
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "D";
											}
											// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												calTyp = "E";
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들Lite
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "Y") {
											// 번들2 일반 번들1 유효(기간중 or 잔여량있음) 구매불가
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 PCRF 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 Lite
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
												if (preBundlePcrfId == prodPcrfId) {
													calTyp = "F";
												}
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
												else {
													calTyp = "E";
												}
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 번들Lite UL
										else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "N") {
											// 모든 경우 번들1 유효(기간중 or 잔여량있음) 구매불가
											calTyp = "E";
										}
									}
									// 번들상품 2개 추가구매불가
									else if (preBundleRemainYn == "T") {
										alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
										return false;
									}
									// 번들구매없음
									else if (preBundleRemainYn == "N") {
										// 번들일반 일반 기본종료까지 예약
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "C";
										}
										// 번들PCRF 일반 기본종료까지 예약
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "C";
										}
										// 번들Lite 기본PCRF 유효(기간중) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											calTyp = "E";
										}
										// 번들Lite UL 기본PCRF 유효(기간중) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
								}
								// 기본Lite
								else if (preBasicPcrfYn == "Y" && preBasicLiteYn == "Y") {
									// 번들상품 1개 유효(잔여량 있음)
									// 번들1 Lite 유효상태에서는 번들2 Lite(PCRF_ID가 동일한 경우)만 구매 가능
									// 나머지는 번들 구매자체가 불가능(기본과 번들이 공존할 수 없음)
									if (preBundleRemainYn == "Y") {
										// 번들Lite
										if (preBundlePcrfYn == "Y" && preBundleLiteYn == "Y") {
											// 번들2 일반 번들1 유효(기간중 or 잔여량있음) 구매불가
											if (prodPcrfYn == "N" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 PCRF 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
												calTyp = "E";
											}
											// 번들2 Lite
											else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
												if (preBundlePcrfId == prodPcrfId) {
													calTyp = "F";
												}
												// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
												else {
													calTyp = "E";
												}
											}
											// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
											else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
												calTyp = "E";
											}
										}
										// 이외 (일반, PCRF, Lite UL)
										else {
											calTyp = "E";
										}
									}
									// 번들상품 2개 추가구매불가
									else if (preBundleRemainYn == "T") {
										alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
										return false;
									}
									// 번들구매없음
									// 기본 Lite 유효상태에서는 번들1 Lite(PCRF_ID가 동일한 경우)만 구매 가능
									// 나머지는 번들 구매자체가 불가능(기본과 번들이 공존할 수 없음)
									else if (preBundleRemainYn == "N") {
										// 번들일반 기본상품 유효(기간중 or 잔여량있음) 구매불가
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "E";
										}
										// 번들PCRF 기본상품 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "E";
										}
										// 번들Lite
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											// 기본과 구매하고자 하는 번들1의 PCRF_ID가 같은 경우 구매가능
											if (preBasicPcrfId == prodPcrfId) {
												calTyp = "F";
											}
											// 기본과 구매하고자 하는 번들1의 PCRF_ID가 다른 경우 구매불가
											else {
												calTyp = "E";
											}
										}
										// 번들Lite UL 기본상품 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
								}
								// 기본Lite UL
								// 기본 Lite UL 유효상태에서는 번들1 구매자체가 불가능(기본과 번들이 공존할 수 없음)
								else if (preBasicPcrfYn == "Y" && preBasicLiteYn == "N") {
									calTyp = "E";
								}
							}
							// RG_PROD_TP : 1 (PCRF_ID 같은 경우 구매가능)
							else if (preBasicRgProdTp == "1") {
								// 번들상품 1개 유효(잔여량 있음)
								if (preBundleRemainYn == "Y") {
									// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
									if (preBundlePcrfId == prodPcrfId) {
										calTyp = "F";
									}
									// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
									else {
										calTyp = "G";
									}
								}
								// 번들상품 2개 추가구매불가
								else if (preBundleRemainYn == "T") {
									alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
									return false;
								}
								// 번들구매없음
								else if (preBundleRemainYn == "N") {
									// 기본과 구매하고자 하는 번들1의 PCRF_ID가 같은 경우 구매가능
									if (preBasicPcrfId == prodPcrfId) {
										calTyp = "F";
									}
									// 기본과 구매하고자 하는 번들1의 PCRF_ID가 다른 경우 구매불가
									else {
										calTyp = "G";
									}
								}
							}
							// RG_PROD_TP : 0 (예약)
							else if (preBasicRgProdTp == "0") {
								// 번들상품 1개 유효(잔여량 있음)
								if (preBundleRemainYn == "Y") {
									// 번들1 번들1종료까지 예약
									calTyp = "D";
								}
								// 번들상품 2개 추가구매불가
								else if (preBundleRemainYn == "T") {
									alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
									return false;
								}
								// 번들구매없음
								else if (preBundleRemainYn == "N") {
									// 번들1 기본종료까지 예약
									calTyp = "C";
								}
							}
						}
					}
					// 기본상품 유효하지 않음(기간만료 or 잔여량없음)
					else if (preBasicRemainYn == "N") {
						// Speed+Yearly(DISC_YN = 'C')
						if (preBasicDiscYn == "C") {
							// 번들상품 1개 유효(잔여량 있음)
							if (preBundleRemainYn == "Y") {
								// 기본상품 공제량이 100 인 경우
								if (preBasicDedtAmt == "100") {
									// 최근 구매 번들이 공제량 100 인 경우
									if (latestBundleDedtAmt == "100") {
										// 최근 구매 번들이 Daily/Night 인 경우(DISC_YN = 'N' AND 공제량 100) 반드시 DISC_YN = 'Y' AND 공제량 100 인 번들 구매해야 함 
										if (latestBundleDiscYn == "N") {
											// 선택한 번들이 Daily/Night 인 경우(DISC_YN = 'N' AND 공제량 100) 구매불가
											if (prodDiscYn == "N" && prodDedtAmt == "100") {
												alert('<spring:message code="MSG.M10.MSG00012" />'); // 최근 구매한 Bundle 상품이 Daily/Night time Pack 입니다. Daily/Night time Pack은 이어서 구매할 수 없습니다.
												return false;
											}
											// 이외
											else {
												// 제한없이 구매 가능
												calTyp = "B";
											}
										}
										// 이외
										else {
											// 제한없이 구매 가능
											calTyp = "B";
										}
									}
									// 이외
									else {
										// 제한없이 구매 가능
										calTyp = "B";
									}
								}
								// 기본상품 공제량이 100 이 아닌 경우
								else {
									// 제한없이 구매 가능
									calTyp = "B";
								}
							}
							// 번들상품 2개 추가구매불가
							else if (preBundleRemainYn == "T") {
								alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
								return false;
							}
							// 번들구매없음
							else if (preBundleRemainYn == "N") {
								// 제한없이 구매 가능
								calTyp = "B";
							}
						}
						// Small bundle add-on(DISC_YN = 'D')
						else if (preBasicDiscYn == "D") {
							// 번들상품 1개 유효(잔여량 있음)
							if (preBundleRemainYn == "Y") {
								// 최근 구매 번들의 유효기간이 종료되지 않은 경우 할인적용, 유효기간 만료된 경우 할인미적용
								// 제한없이 구매 가능
								calTyp = "B";
							}
							// 번들상품 2개 추가구매불가
							else if (preBundleRemainYn == "T") {
								alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
								return false;
							}
							// 번들구매없음
							else if (preBundleRemainYn == "N") {
								// 제한없이 구매 가능
								calTyp = "B";
							}
						}
						// 기존
						else {
							// RG_PROD_TP : X (기존)
							if (preBundleRgProdTp == "X") {
								// 번들상품 1개 유효(잔여량 있음)
								if (preBundleRemainYn == "Y") {
									// 번들일반
									if (preBundlePcrfYn == "N" && preBundleLiteYn == "X") {
										// 번들2 일반 바로 구매가능
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "B";
										}
										// 번들2 PCRF 바로 구매가능
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "B";
										}
										// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											calTyp = "E";
										}
										// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
									// 번들PCRF
									else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "X") {
										// 번들2 일반 번들1 종료까지 예약
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "D";
										}
										// 번들2 PCRF 번들1 종료까지 예약
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "D";
										}
										// 번들2 Lite 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											calTyp = "E";
										}
										// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
									// 번들Lite
									else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "Y") {
										// 번들2 일반 번들1 유효(기간중 or 잔여량있음) 구매불가
										if (prodPcrfYn == "N" && prodLiteYn == "X") {
											calTyp = "E";
										}
										// 번들2 PCRF 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "X") {
											calTyp = "E";
										}
										// 번들2 Lite
										else if (prodPcrfYn == "Y" && prodLiteYn == "Y") {
											// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
											if (preBundlePcrfId == prodPcrfId) {
												calTyp = "F";
											}
											// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
											else {
												calTyp = "E";
											}
										}
										// 번들2 Lite UL 번들1 유효(기간중 or 잔여량있음) 구매불가
										else if (prodPcrfYn == "Y" && prodLiteYn == "N") {
											calTyp = "E";
										}
									}
									// 번들Lite UL
									else if (preBundlePcrfYn == "Y" && preBundleLiteYn == "N") {
										// 모든 경우 번들1 유효(기간중 or 잔여량있음) 구매불가
										calTyp = "E";
									}
								}
								// 번들상품 2개 추가구매불가
								else if (preBundleRemainYn == "T") {
									alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
									return false;
								}
								// 번들구매없음
								// 모든 번들 즉시 구매가능(일반, PCRF, Lite, Lite UL)
								else if (preBundleRemainYn == "N") {
									calTyp = "B";
								}
							}
							// RG_PROD_TP : 1 (PCRF_ID 같은 경우 구매가능)
							else if (preBundleRgProdTp == "1") {
								// 번들상품 1개 유효(잔여량 있음)
								if (preBundleRemainYn == "Y") {
									// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 같은 경우 구매가능
									if (preBundlePcrfId == prodPcrfId) {
										calTyp = "F";
									}
									// 번들1과 구매하고자 하는 번들2의 PCRF_ID가 다른 경우 구매불가
									else {
										calTyp = "G";
									}
								}
								// 번들상품 2개 추가구매불가
								else if (preBundleRemainYn == "T") {
									alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
									return false;
								}
								// 번들구매없음
								else if (preBundleRemainYn == "N") {
									// 모든 번들 즉시 구매가능(일반, PCRF, Lite, Lite UL)
									calTyp = "B";
								}
							}
							// RG_PROD_TP : 0 (예약)
							else if (preBundleRgProdTp == "0") {
								// 번들상품 1개 유효(잔여량 있음)
								if (preBundleRemainYn == "Y") {
									// 번들1 번들1종료까지 예약
									calTyp = "D";
								}
								// 번들상품 2개 추가구매불가
								else if (preBundleRemainYn == "T") {
									alert('<spring:message code="MSG.M01.MSG00045" />'); // 구매한 Bundle 상품이 두 개 이상입니다. 더 이상 구매를 할 수 없습니다.
									return false;
								}
								// 번들구매없음
								else if (preBundleRemainYn == "N") {
									// 모든 번들 즉시 구매가능(일반, PCRF, Lite, Lite UL)
									calTyp = "B";
								}
							}
						}
					}
					
				}
				// 기존체크
				else {
					if (preBundleRemainYn == "Y") {
						alert('<spring:message code="MSG.M15.MSG00014" />'); // Bundle 상품의 잔량이 남아 있어 더 이상 구매를 할 수 없습니다.
						return false;
					}
				}
			}

			// Lite, Lite UL상품의 영향으로 구매불가
			if (calTyp == "E") {
				alert('<spring:message code="MSG.M01.MSG00060" />'); // 기본상품 혹은 구매한 번들이 남아 있어 구매를 할 수 없습니다.
				return false;
			}
			
			// K-Movie, Netflix 와 같이 공제 두개짜리 : 번들구매시 유효기간에 PCRF_ID 다른 경우 구매불가(RG_PROD_TP : 1)
			if (calTyp == "G") {
				alert('<spring:message code="MSG.M01.MSG00060" />'); // 기본상품 혹은 구매한 번들과 다른 상품은 구매를 할 수 없습니다.
				return false;
			}
			
			var vPcrfYn = prodPcrfYn;
			var vLiteYn = prodLiteYn;
			var vRgProdTp = prodRgProdTp;
			
			// RG_PROD_TP : X 인 경우(일반 UL인 경우에만 해당)
			if (vPcrfYn == "X") {
				if (vPcrfYn == "Y" && vLiteYn == "X") {
					alert('<spring:message code="MSG.M07.MSG00078" />'); // 선택한 번들은 Unlimited 상품입니다. 사용량이 예정량된 사용량을 초과할 경우, 속도가 감소하게 됩니다.
				}
			}
			
		    // 충전유형
		    var vRchrgTp = rchrgTp;

			// 할인상품(Dailiy : 12:00 ~ 18:00 사용가능) 시간대별 구매시 익일적용 여부 체크를 위한 var
			var vDnType = prodDnType;
			var vGetDayTime = new Date();
			var todayDt = vGetDayTime.getFullYear() + '' + (vGetDayTime.getMonth() + 1) + vGetDayTime.getDate();
			var vTodayTime = vGetDayTime.getTime() + '' + vGetDayTime.getMinutes() + '' vGetDayTime.getSeconds();
			vGetDayTime.setDate(vGetDayTime.getDate() + 1);
			var vAddDate = vGetDayTime.getFullYear() + '' + (vGetDayTime.getMonth() + 1) + vGetDayTime.getDate();
			
			// Holiday Pack 구매 여부 체크를 위한 선택 번들의 휴일타입 조회
			var vHoliTp = prodHoliYn; // HolidayPack의 휴일 타입(N이 아닌)
			var vPromoDt = $('#promoDt').val(); // 오늘이 프로모션 지정일인 경우 1회만 구매 가능
			var vPromoPurCnt = #('#purCnt').val(); // 당월 프로모션 지정일에 HolidayPack 구매횟수
		    
		    if (vRchrgTp == "500" || vRchrgTp == "600" || vRchrgTp == "700") {
				if (vHoliTp != "N") {
					if(vPromoDt == "99991231") {
						alert('<spring:message code="MSG.M08.MSG00053" />'); // 이번달 프로모션 지정일이 등록되지 않았습니다.
						return false;
					}
					
					if(vPromoDt != todayDt) {
						alert('<spring:message code="MSG.M15.MSG00023" />'); // Holiday Pack은 프로모션 지정일에만 구매할 수 있습니다.
						return false;
					}
					
					if(vPromoDt == todayDt && vPromoPurCnt >= 1) {
						alert('<spring:message code="MSG.M15.MSG00022" />'); // Holiday Pack은 프로모션 지정일에 1회만 구매할 수 있습니다.
						return false;
					}
				}
		    }

		    // 충전하시겠습니니까?
		    var confirm = confirm('<spring:message code="MSG.M10.MSG00033" />');
			
			if (confirm == false) { 
		        return false;
		    }

		    var rchrgDt = '';
		    
			//ds_tcmbl_rchrg_hist.SetColumn(0,"RCHRG_DT", cal_rchrgDt.Value);
			if (calTyp == "A") {
				rchrgDt = $('#rchrgDtPop').val();
			} else if (calTyp == "B") {
				//ds_tcmbl_rchrg_hist.SetColumn(0,"RCHRG_DT", cal_rchrgDt.Value);
				// DN_TYPE (Daily time : X)
				if (vDnType == "X"){
					// 18시 이상은 충전일 익일 처리
					if (vTodayTime >= "180000") {
						rchrgDt = vAddDate;
					}
					// 18시 미만은 충전일 당일 처리
					else {
						rchrgDt = $('#rchrgDtPop').val();
					}
				}
				// 이외
				else {
					rchrgDt = $('#rchrgDtPop').val();
				}
			}
			// 기본 종료시까지 예약
			else if (calTyp == "C") {
				rchrgDt = preBasicTermDt;
			}
			// 번들1 종료시까지 예약
			else if (calTyp == "D") {
				rchrgDt = preBundleTermDt;
			}
			// Lite, Lite UL상품의 영향으로 구매불가
			// + K-Movie, Netflix 와 같이 공제 두개짜리 : 번들구매시 유효기간에 PCRF_ID 다른 경우 구매불가(RG_PROD_TP : 1)
			else if (calTyp == "E") {
			}
			// Lite상품(PCRF_ID가 동일한 경우) 즉시구매가능
			// + K-Movie, Netflix 와 같이 공제 두개짜리 : 번들구매시 유효기간에 PCRF_ID 같은 경우 구매가능(RG_PROD_TP : 1)
			else if (calTyp == "F") {
				rchrgDt = $('#rchrgDtPop').val();
			}

			var custId = '${ctrtInfo.custId}';
			var ctrtId = '${ctrtInfo.ctrtId}';
			var soId = '${ctrtInfo.soId}';
			var prodCd = $('#prodProdCd').val();
			var chrgAmt = $('#rchrgAmtPop').val();
			var rchrgTp = $('#rchrgTp').val();
			var rchrgStat = '10'
			var svcTelNo = $('#svcTelNoPop').val();
			var tgtCustId = $('#tgtCustIdPop').val();
			var tgtCtrtId = $('#tgtCtrtIdPop').val();
			var tgtSvcTelNo = $('#tgtSvcTelNoPop').val();
			var voSeqNo = $('#voSeqNoPop').val();
			var voBarCd = $('#voBarCdPop').val();
			var qosAmt = $('#qosAmtPop').val();
			var effPeriod = $('#effPeriodPop').val();
			var apprTp = '';
			var pcrfYn = '';
			var dhType = '';
			var dnType = '';
			
			if (vRchrgTp == "100" || vRchrgTp == "600" || vRchrgTp == "700") {
				apprTp = '00';
			}
			
			// 예약처리위한 VALUE
			// 700 추가
			if (vRchrgTp == "500" || vRchrgTp == "600" || vRchrgTp == "700") {
				if (calTyp == "C" || calTyp == "D") {
					pcrfYn = 'Y';
				} else {
					pcrfYn = 'N';
				}

				dhType = $('#dhTypePop').val();
				dnType = $('#dnTypePop').val();
			} else {
				pcrfYn = 'O';
			}


			var param = {
				custId : custId
				, ctrtId : ctrtId
				, soId : soId
				, prodCd : prodCd
				, chrgAmt : chrgAmt
				, rchrgTp : rchrgTp
				, rchrgStat : rchrgStat
				, svcTelNo : svcTelNo
				, tgtCustId : tgtCustId
				, tgtCtrtId : tgtCtrtId
				, tgtSvcTelNo : tgtSvcTelNo
				, voSeqNo : voSeqNo
				, voBarCd : voBarCd
				, qosAmt : qosAmt
				, effPeriod : effPeriod
				, apprTp : apprTp
				, pcrfYn : pcrfYn
				, dhType : dhType
				, dnType : dnType
			};

			$.ajax({
				url : 'addCharge.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					var result = data.result;

					if(result == 0){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					} else {
						var errorMsg = data.errorMsg;
						alert(errorMsg);
						return;
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});


		});

		disableInputFields();
		disableSearchButtons();
		enableInputField('rchrgAmtPop');

		settingDatepicker();
		$('#rchrgDtPop').datepicker('setDate', new Date());

		$('#rchrgTpPop').change(function() {
			$("#chargeForm input").val("");
			$('#rchrgDtPop').datepicker('setDate', new Date());
			var rchrgTp = $('#rchrgTpPop').val();

			disableInputFields();
			disableSearchButtons();

			if (rchrgTp == '100') {
				// 데이터충전
				enableInputField('rchrgAmtPop');
			} else if (rchrgTp == '300') {
				// 바우처 충전
				enableATag('btnSearchVoucher');
			} else if (rchrgTp == '400') {
				// 선물하기
				enableInputField('rchrgAmtPop');
				enableATag('btnSearchCustPop');
				enableATag('btnSearchCtrtPop');
			} else if (rchrgTp == '500') {
				// 번들상품 구매
				enableATag('btnSearchProdPop');
			} else if (rchrgTp == '600') {
				// 충전/번들구매
				enableATag('btnSearchProdPop');
			} else if (rchrgTp == '700') {
				// 상품 바우처 충전
				enableATag('btnSearchVoucher');
			}
		});
	});

	function setVoucherInfo(data) {
		$('#voucherSoNm').val(data.soNm);
		$('#voucherVoBarCd').val(data.voBarCd);
		$('#voucherVoSeqNo').val(data.voSeqNo);
		$('#voucherItemNm').val(data.itemNm);
		$('#voucherVoIssueAmt').val(data.voIssueAmt);
		$('#voucherVoProdNm').val(data.voProdNm);
		$('#voucherVoIssueDt').val(data.voIssueDt);
		$('#voucherVoExpiredDt').val(data.voExpiredDt);

		$('#voSeqNoPop').val(data.voSeqNo);
		$('#voBarCdPop').val(data.voBarCd);

		$('#effPeriodPop').val(data.effPeriod);
		$('#qosAmtPop').val(data.qosAmt);
		$('#dhTypePop').val(data.dhType);
		$('#dnTypePop').val(data.dnType);
	}

	function setCustomerInfo(data) {
		$('#tgtCustIdPop').val(data.custId);
		$('#tgtCustNmPop').val(data.custNm);
		$('#tgtCustSoIdPop').val(data.soId);
		$('#tgtCustSoNmPop').val(data.soNm);
	}

	function setProdData(data) {
		$('#prodSoId').val(data.soId);
		$('#prodDhType').val(data.dhType);
		$('#prodPcrfId').val(data.pcrfId);
		$('#prodLiteYn').val(data.liteYn);
		$('#prodPdvcYn').val(data.pdvcYn);
		$('#prodDiscYn').val(data.discYn);
		$('#prodHoliYn').val(data.holiYn);
		$('#prodRgProdTp').val(data.rgProdTp);
		$('#prodDedtAmt').val(data.dedtAmt);
		$('#prodDnType').val(data.dnType);
		$('#prodSoNm').val(data.soNm);
		$('#prodProdCd').val(data.prodCd);
		$('#prodProdNm').val(data.prodNm);
		$('#prodProdAmt').val(data.prodAmt);
		$('#prodQosAmt').val(data.qosAmt);
		$('#prodEffPeriod').val(data.effPeriod);
		$('#prodPcrfYn').val(data.pcrfYn);
		$('#prodRcmdYn').val(data.rcmdYn);

		$('#effPeriodPop').val(data.effPeriod);
		$('#qosAmtPop').val(data.qosAmt);
		$('#dhTypePop').val(data.dhType);
		$('#dnTypePop').val(data.dnType);
	}

	function disableInputFields() {
		disableInputField('svcTelNoPop');
		disableInputField('ctrtIdPop');
		disableInputField('rmnAmtPop');
		disableInputField('rchrgAmtPop');
		disableInputField('voSeqNoPop');
		disableInputField('voBarCdPop');
		disableInputField('tgtCustNmPop');
		disableInputField('tgtCtrtIdPop');
		disableInputField('tgtSvcTelNoPop');
		disableInputField('prodNmPop');
		disableInputField('svcInactDtPop');
		disableInputField('apprTpPop');
		disableInputField('qosAmtPop');
		disableInputField('actDtPop');
		disableInputField('debtAmtPop');
		disableInputField('pcrfYnPop');
		disableInputField('pcrfIdPop');
		disableInputField('liteYnPop');
		disableInputField('discYnPop');
	}

	function disableSearchButtons() {
		disableATag('btnSearchVoucher');
		disableATag('btnSearchCustPop');
		disableATag('btnSearchCtrtPop');
		disableATag('btnSearchProdPop');
	}

	function disableButton(id) {
		$('#' + id).addClass('white-btn');
		$('#' + id).removeClass('grey-btn');
		$('#' + id).addClass('not-active');
		$('#' + id).attr('disabled',true);
	}

	function enableButton(id) {
		$('#' + id).addClass('grey-btn');
		$('#' + id).removeClass('white-btn');
		$('#' + id).removeClass('not-active');
		$('#' + id).removeAttr('disabled');
	}

	function disableInputField(id) {
		$("#" + id).addClass('not-active'); 
		$("#" + id).attr('disabled', true);
	}

	function enableInputField(id) {
		$("#" + id).removeClass('not-active');
		$("#" + id).removeAttr('disabled');
	}

	function disableATag(id) {
		$('#' + id).addClass('not-active');
		$('#' + id).attr('disabled',true);
	}

	function enableATag(id) {
		$('#' + id).removeClass('not-active');
		$('#' + id).removeAttr('disabled');
	}

</script>

<div class="layer_top">
     <div class="title"><spring:message code="LAB.M10.LAB00082"/><!-- 충전 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">          
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00186"/><!-- 서비스번호 --></th>
				<td><input type="text" id="svcTelNoPop" name="svcTelNoPop" class="w150" /></td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --></th>
				<td><input type="text" id="ctrtIdPop" name="ctrtIdPop" class="w150" /></td>
				<th><spring:message code="LAB.M09.LAB00040"/><!-- 잔액 --></th>
				<td><input type="text" id="rmnAmtPop" name="rmnAmtPop" class="w150" value="${rmnAmt}" /></td>
			</tr>
		</thead>
	</table> 
</div>

<!-- center -->
<div class="layer_box">          
	<form id="chargeForm">
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M10.LAB00085"/><!-- 충전유형 --></th>
				<td>
					<select class="w150" id="rchrgTpPop" name="rchrgTpPop">
						<c:forEach items="${rchrgTpList}" var="rchrgTp" varStatus="status">
							<option value="${rchrgTp.commonCd}">${rchrgTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M10.LAB00086"/><!-- 충전일자 --></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="rchrgDtPop" name="rchrgDtPop" class="datepicker" readonly="readonly" />
						<a href="#" id="rchrgDtPopSel" class="btn_cal"></a>
					</div>
				</td>
				<th><spring:message code="LAB.M10.LAB00083"/><!-- 충전금액 --></th>
				<td><input type="text" id="rchrgAmtPop" name="rchrgAmtPop" class="w150" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00015"/><!-- 바우처일련번호 --></th>
				<td colspan="2">
					<div class="inp_date w200">
						<input type="text" id="voSeqNoPop" name="voSeqNoPop" class="w170" disabled="disabled" />
						<a href="#" id="btnSearchVoucher" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00004"/><!-- 바우처관리번호 --></th>
				<td colspan="2"><input type="text" id="voBarCdPop" name="voBarCdPop" class="w200" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00049"/><!-- 대상고객 --></th>
				<td>
					<div class="inp_date w150">
						<input type="hidden" id="tgtCustIdPop" />
						<input type="hidden" id="tgtCustSoIdPop" />
						<input type="hidden" id="tgtCustSoNmPop" />
						<input type="text" id="tgtCustNmPop" name="tgtCustNmPop" class="w120" disabled="disabled" />
						<a href="#" id="btnSearchCustPop" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00047"/><!-- 대상계약ID --></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="tgtCtrtIdPop" name="tgtCtrtIdPop" class="w120" disabled="disabled" />
						<a href="#" id="btnSearchCtrtPop" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00054"/><!-- 대상서비스번호 --></th>
				<td><input type="text" id="tgtSvcTelNoPop" name="tgtSvcTelNoPop" class="w150" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00114"/><!-- 상품 --></th>
				<td>
					<div class="inp_date w150">
						<input type="hidden" id="prodIdPop" />
						<input type="text" id="prodNmPop" name="prodNmPop" class="w150" disabled="disabled" />
						<a href="#" id="btnSearchProdPop" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M07.LAB00189"/><!-- 서비스종료일시 --></th>
				<td><input type="text" id="svcInactDtPop" name="svcInactDtPop" class="w150" /></td>
				<th><spring:message code="LAB.M07.LAB00273"/><!-- 승인유형 --></th>
				<td><input type="text" id="apprTpPop" name="apprTpPop" class="w150" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M11.LAB00015"/><!-- 쿼터 --></th>
				<td><input type="text" id="qosAmtPop" name="qosAmtPop" class="w150" /></td>
				<th><spring:message code="LAB.M08.LAB00107"/><!-- 유효기간 --></th>
				<td><input type="text" id="actDtPop" name="actDtPop" class="w150" /></td>
				<th><spring:message code="LAB.M15.LAB00028"/><!-- Deduction Amount --></th>
				<td><input type="text" id="debtAmtPop" name="debtAmtPop" class="w150" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M15.LAB00070"/><!-- PCRF (Y/N, ID) --></th>
				<td>
					<input type="text" id="pcrfYnPop" name="pcrfYnPop" class="w90" />
					<input type="text" id="pcrfIdPop" name="pcrfIdPop" class="w90" />
				</td>
				<th><spring:message code="LAB.M15.LAB00055"/><!-- LINE_YN --></th>
				<td><input type="text" id="liteYnPop" name="liteYnPop" class="w150" /></td>
				<th><spring:message code="LAB.M15.LAB00029"/><!-- Discount_Type --></th>
				<td><input type="text" id="discYnPop" name="discYnPop" class="w150" /></td>
			</tr>
		</thead>
	</table>

	<!-- common -->
	<input type="hidden" id="qosAmtPop" />
	<input type="hidden" id="effPeriodPop" />
	<input type="hidden" id="dhTypePop" />
	<input type="hidden" id="dnTypePop" />

	<!-- voucher -->
	<input type="hidden" id="voucherSoNm" />
	<input type="hidden" id="voucherVoBarCd" />
	<input type="hidden" id="voucherVoSeqNo" />
	<input type="hidden" id="voucherItemNm" />
	<input type="hidden" id="voucherVoIssueAmt" />
	<input type="hidden" id="voucherVoProdNm" />
	<input type="hidden" id="voucherVoIssueDt" />
	<input type="hidden" id="voucherVoExpiredDt" />
	
	<!-- prod -->
	<input type="hidden" id="prodSoId" />
	<input type="hidden" id="prodDhType" />
	<input type="hidden" id="prodPcrfId" />
	<input type="hidden" id="prodLiteYn" />
	<input type="hidden" id="prodPdvcYn" />
	<input type="hidden" id="prodDiscYn" />
	<input type="hidden" id="prodHoliYn" />
	<input type="hidden" id="prodRgProdTp" />
	<input type="hidden" id="prodDedtAmt" />
	<input type="hidden" id="prodDnType" />
	<input type="hidden" id="prodSoNm" />
	<input type="hidden" id="prodProdCd" />
	<input type="hidden" id="prodProdNm" />
	<input type="hidden" id="prodProdAmt" />
	<input type="hidden" id="prodQosAmt" />
	<input type="hidden" id="prodEffPeriod" />
	<input type="hidden" id="prodPcrfYn" />
	<input type="hidden" id="prodRcmdYn" />

	</form>
</div>

<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnChargePop" href="#" ><span class="confirm_icon"><spring:message code="LAB.M10.LAB00082" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!-- preBasicProdYnInfo -->
<input type="hidden" id="preBasicProdYnInfoRemainYn" value="${preBasicProdYnInfo.remainYn}" />
<input type="hidden" id="preBasicProdYnInfoPcrfId" value="${preBasicProdYnInfo.pcrfId}" />
<input type="hidden" id="preBasicProdYnInfoPcrfYn" value="${preBasicProdYnInfo.pcrfYn}" />
<input type="hidden" id="preBasicProdYnInfoLiteYn" value="${preBasicProdYnInfo.liteYn}" />
<input type="hidden" id="preBasicProdYnInfoDiscYn" value="${preBasicProdYnInfo.discYn}" />
<input type="hidden" id="preBasicProdYnInfoTermDt" value="${preBasicProdYnInfo.termDt}" />
<input type="hidden" id="preBasicProdYnInfoRgProdTp" value="${preBasicProdYnInfo.rgProdTp}" />
<input type="hidden" id="preBasicProdYnInfoProdCd" value="${preBasicProdYnInfo.prodCd}" />
<input type="hidden" id="preBasicProdYnInfoDedtAmt" value="${preBasicProdYnInfo.dedtAmt}" />

<!-- preBundleProdYnInfo -->
<input type="hidden" id="preBundleProdYnInfoRemainYn" value="${preBundleProdYnInfo.remainYn}" />
<input type="hidden" id="preBundleProdYnInfoPcrfId" value="${preBundleProdYnInfo.pcrfId}" />
<input type="hidden" id="preBundleProdYnInfoPcrfYn" value="${preBundleProdYnInfo.pcrfYn}" />
<input type="hidden" id="preBundleProdYnInfoLiteYn" value="${preBundleProdYnInfo.liteYn}" />
<input type="hidden" id="preBundleProdYnInfoTermDt" value="${preBundleProdYnInfo.termDt}" />
<input type="hidden" id="preBundleProdYnInfoRgProdTp" value="${preBundleProdYnInfo.rgProdTp}" />

<!-- latestBundleProdInfo -->
<input type="hidden" id="latestBundleProdInfoProdCd" value="${latestBundleProdInfo.prodCd}" />
<input type="hidden" id="latestBundleProdInfoDiscYn" value="${latestBundleProdInfo.discYn}" />
<input type="hidden" id="latestBundleProdInfoDedtAmt" value="${latestBundleProdInfo.dedtAmt}" />
<input type="hidden" id="latestBundleProdInfoRchrgTermDt" value="${latestBundleProdInfo.rchrgTermDt}" />

<!-- promoCurrentInfo -->
<input type="hidden" id="promoCurrentInfoPromoDt" value="${promoCurrentInfo.promoDt}" />
<input type="hidden" id="promoCurrentInfoPurCnt" value="${promoCurrentInfo.purCnt}" />

<!-- notResPvCount -->
<input type="hidden" id="notResPvCount" value="${notResPvCount}" >
