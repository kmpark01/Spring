//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/modify.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

const f = document.forms[0];
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');

//각 버튼 클릭 이벤트
document.querySelectorAll('button').forEach(btn => {
  btn.addEventListener('click', () => {
    let type = btn.getAttribute("id");
    let bno = f.bno.value;

    if(type === 'modifyBtn'){
      modify();
    }else if(type === 'removeBtn'){
      remove();
    }else if(type === 'indexBtn'){
    	location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
    }
  });
});

// 게시글 수정 이벤트
function modify(){
  if(!f.title.value){
    alert("제목을 입력하세요.");
    return;
  }

  if(!f.content.value){
    alert("내용을 입력하세요.");
    return;
  }
  
  f.action = `/board/modify?pageNum=${pageNum}&amount=${amount}`;
  f.submit();
}

// 게시글 삭제 이벤트
function remove(){
  let bnoEle = f.bno; // bno를 담고 있는 input 태그
  f.innerHTML = ''; // form 태그 내부 비우기
  f.appendChild(bnoEle); // form 태그 내부에 bno 추가

  f.action = `/board/remove?pageNum=${pageNum}&amount=${amount}`;
  f.submit();
}