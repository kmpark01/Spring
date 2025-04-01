//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/boardList.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// url에서 pageNum, amount 가져오기
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
if(!pageNum || !amount){
	pageNum = 1;
	amount = 10;
}
setStorageData(pageNum, amount);

// 게시글 등록 버튼 클릭 이벤트
document.querySelector("#registerBtn").addEventListener('click', () => {
  location.href = `/board/register?pageNum=${pageNum}&amount=${amount}`;
});

// 글 제목 클릭 이벤트
let aEles = document.querySelectorAll('tbody a');
aEles.forEach(a => {
  a.addEventListener('click', e => {
    // 기본 이벤트 방지
    e.preventDefault();
    // href 값 가져오기(bno)
    let bno = a.getAttribute('href');
    // /board/get 경로로 bno 값 전달하기
    location.href = `/board/get?bno=${bno}&pageNum=${pageNum}&amount=${amount}`;
  })
})

//페이지 버튼 클릭 이벤트
let pEles = document.querySelectorAll(".page-nation li a");
pEles.forEach( pEle => {
	pEle.addEventListener('click', function(e){
		e.preventDefault();
		
		// url에서 pageNum, amount 가져오기
		let pageNum = this.getAttribute('href');
		let amount = new URLSearchParams(location.search).get('amount');

		location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
		
	});
});


