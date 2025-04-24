
const obj = {
    name:'LEE',
    age:2,
    hobby: ['A','B']
};

const jsonstr = JSON.stringify(obj);
console.log(jsonstr);


const todos = [
    { id: 1, content: 'HTML', completed: false },
    { id: 2, content: 'CSS', completed: true },
    { id: 3, content: 'Javascript', completed: false }
];


// 배열을 JSON 포맷의 문자열로 변환한다.
const json = JSON.stringify(todos, null, 2);
console.log(typeof json, json);



const obj1 = {
    name: 'Lee',
    age: 20,
    alive: true,
    hobby: ['traveling', 'tennis']
};


// 객체를 JSON 포맷의 문자열로 변환한다.
const json1 = JSON.stringify(obj1);


// JSON 포맷의 문자열을 객체로 변환한다.
const parsed = JSON.parse(json1);
console.log(typeof parsed, parsed);
// object {name: "Lee", age: 20, alive: true, hobby: ["traveling", "tennis"]}
