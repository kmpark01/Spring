//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/register.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);
const f = document.forms[0];
// url 파싱
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');

// 버튼들 클릭 이벤트
// 새 게시글 등록 버턴 - register() 함수 호출
document.querySelectorAll("button").forEach(btn => {
	// 🔒 기본 submit 방지
  btn.setAttribute("type", "button"); 
  btn.addEventListener('click', () => {
    let type = btn.getAttribute("id");
    
    if(type === 'registerBtn'){
      register();
    }else if(type === 'resetBtn'){
      f.reset();
    }else if(type === 'indexBtn'){
    	location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
    }
  })
})

function register(){
  if(!f.title.value){
    alert("제목을 입력하세요.");
    return;
  }
  if(!f.writer.value){
    alert("작성자을 입력하세요.");
    return;
  }
  if(!f.content.value){
    alert("내용을 입력하세요.");
    return;
  }
  
  // 첨부파일 li들 가져와서 반복문 돌리기
  // 해당 li에 포함된 속동들 꺼내서 화면에 출력
  let str = '';
  document.querySelectorAll('.uploadResult ul li').forEach((li, index) => {
    let path = li.getAttribute('path');
    let uuid = li.getAttribute('uuid');
    let fileName = li.getAttribute('fileName');
    
    str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}">`;
    str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}">`;
    str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}">`;
  });

//  f.innerHTML += str;
  f.insertAdjacentHTML('beforeend', str);
  console.log(f);
  f.action = '/board/register';
  f.submit();
}