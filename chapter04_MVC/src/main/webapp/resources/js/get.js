//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/get.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

let f = document.forms[0];
// url에서 bno, pageNum, amount 가져오기
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
let bno = new URLSearchParams(location.search).get('bno');


// 각 버튼 클릭 이벤트
document.querySelectorAll('button').forEach(btn => {
  btn.addEventListener('click', () => {
    let type = btn.getAttribute("id");
    let bno = f.bno.value;
    

    if(type === 'modifyBtn'){
      location.href = `/board/modify?bno=${bno}&pageNum=${pageNum}&amount=${amount}`;
    }else if(type === 'indexBtn'){
      location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
    }else if(type === 'replyBtn'){
      registerModalPage();
    }else if(type === 'closeModalBtn'){
      closeModal();
    }else if(type === 'addReplyBtn'){
      // 댓글 등록 실행 버튼
      registerReply();
    }else if(type === 'modifyReplyBtn'){
      // 댓글 수정 버튼
      modifyReply();
    }else if(type === 'removeReplyBtn'){
      // 댓글 삭제 버튼
      removeReply();
    }
  });
});

// --------------------------- 댓글 관련 스크립트 ------------------------
const rs = replyService;

// 댓글 목록 가져오기
showList();
function showList(){
  const replyUL = document.querySelector(".chat");
  let msg = '';

  rs.getList(bno, jsonArray => {
    // list이니까, 반복문 통해서 vo 콘솔에 출력
    jsonArray.forEach(json => {
      msg += `<li data-rno="${json.rno}" onclick="modifyModalPage(this)">`;
      msg +=  `<div>`;
      msg +=    `<div class="chat-header">`;
      msg +=      `<strong>${json.replyer}</strong>`;
      msg +=      `<small class="pull-right">${displayTime(json.updateDate)}</small>`;
      msg +=    `</div>`;
      msg +=    `<p>${json.reply}</p>`;
      msg +=  `</div>`;
      msg += `</li>`;
    });
    replyUL.innerHTML = msg;
  });
}

// --------------- 모달 관련 스크립트 ------------------
const modal = document.querySelector("#modal");
const inputReply = document.querySelector("input[name='reply']");
const inputReplyer = document.querySelector("input[name='replyer']");
const inputReplydate = document.querySelector("input[name='replydate']");
const addReplyBtn = document.querySelector("#addReplyBtn");
const modifyReplyBtn = document.querySelector("#modifyReplyBtn");
const removeReplyBtn = document.querySelector("#removeReplyBtn");
let rno = null;

function openModal(){
  modal.style.display = "block";
}

function closeModal(){
  modal.style.display = "none";
}

function registerModalPage(){
  // 보여질 목록 수정
  regReplyModalStyle();
  // input 내용 초기화
  inputReply.value = '';
  inputReplyer.value = '';
  openModal();
}

// 댓글 등록 버튼 클릭 시 스타일 변경 함수
function regReplyModalStyle(){
  inputReplydate.closest("div").classList.add('hide');
  modifyReplyBtn.classList.add('hide');
  removeReplyBtn.classList.add('hide');
  inputReplyer.removeAttribute('readonly');
  addReplyBtn.classList.remove('hide');
}

// 댓글 삽입 함수
function registerReply(){
  if(!inputReply.value || !inputReplyer.value){
    alert("모든 내용을 입력하세요.");
    return;
  }
  rs.add({
    reply : inputReply.value,
    replyer : inputReplyer.value,
    bno : bno
  }, 
  function(result){
    console.log("result : " + result);
    closeModal();
    showList();
  });
}


// 댓글 자세히 보기
function modifyModalPage(li){
  // 스타일 변경
  modifyReplyModalStyle();
  
  // 값 바인딩
  rno = li.getAttribute('data-rno');
  const li_replyer = li.querySelector("strong").textContent;
  const li_replydate = li.querySelector("small").textContent;
  const li_relply = li.querySelector("p").textContent;
  
  inputReply.value = li_relply;
  inputReplyer.value = li_replyer;
  inputReplydate.value = li_replydate;
  // 모달 창 열기
  openModal();
  
}

// 댓글 수정
function modifyReply(){
  if(!inputReply.value){
    alert("수정할 댓글을 입력하세요");
    return;
  }
  rs.update({
    reply : inputReply.value,
    rno : rno
  },
  function(result){
    closeModal();
    showList();
  }); 
}

// 댓글 삭제
function removeReply(){
  rs.remove(rno, function(result){
    closeModal();
    showList();
  });
}


// 댓글 목록 클릭 시 스타일 변경 함수
function modifyReplyModalStyle(){
  inputReplydate.closest("div").classList.remove('hide');
  modifyReplyBtn.classList.remove('hide');
  removeReplyBtn.classList.remove('hide');
  addReplyBtn.classList.add('hide');
  inputReplyer.setAttribute('readonly', true);
  inputReplydate.setAttribute('readonly', true);
}


// rs.add({
//   reply : 'JS TEST',
//   replyer : 'TESTER',
//   bno : 30
// }, 
// function(result){
//   alert("result : " + result);
// });

//rs.getList(bno, jsonArray => {
//  // list이니까, 반복문 통해서 vo 콘솔에 출력
//  jsonArray.forEach(json => {
//    console.log(json)
//  })
//})

// rs.remove(3, function(result){
//   alert("result : " + result);
// });

// rs.update({
//   reply : "수정 댓글",
//   rno : 5
// },
// function(result){
//   alert("result : " + result);
// });

// rs.get(5, function(result){
//   alert("result : " + result);
// });


// 유닉스 시간 변경
function displayTime(unixTimeStemp){
  let myDate = new Date(unixTimeStemp);

  let date = myDate.getFullYear() + "-" +
             (myDate.getMonth() + 1) + "-" +
             myDate.getDate();

  return date;
}
