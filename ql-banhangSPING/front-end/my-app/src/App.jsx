import { useState } from 'react'
import { useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0);

  useEffect(()=>{
  console.log("Component đã được render lần đầu tiên");
  },[])
  useEffect(() => {
    console.log("Chạy mỗi lần render");
  });
  
useEffect(() => {
  console.log("Count thay đổi:", count);
  console.log("Đây là callback chạy lại mỗi khi count thay đổi");
}, [count]);  // Callback chạy lại mỗi khi count thay đổi

  const [value, setValue] = useState('');
  const products=[
    {id: 1, name: 'Sản phẩm 1', price: 100},
    {id: 2, name: 'Sản phẩm 2', price: 200}
  ]
  const name = ['Bảo', 'Linh', 'Lam', 'Ngọc', 'Thành'];
  const isLoggedIn = true;
  return(
    <>
    <button onClick={()=>setCount(count+1)}>Bấm vào đây</button>
      <div>
        {isLoggedIn ?(<h3>Bạn đã đăng nhập</h3>):(<h3>Bạn chưa đăng nhập</h3>)}
      </div>
      <div>
        {products.map(product => (
        <div key={product.id}>
          <h3>{product.name}</h3>
          <p>Giá: {product.price}K</p>
        </div>
      ))}
      </div>
      <div>
        {name.map((maps, index) => (
        <h3 key={index}>{maps}</h3>
        ))}
      </div>

      <button onClick={()=> alert('Hello World')}>Click me</button>
      <input onChange={(e)=> setValue(e.target.value)} placeholder='Nhập vào đây' />
      <p>Đây là dòng của onChange: {value}</p>
      <form onSubmit={(e) => {
        e.preventDefault();
        const name = e.target.name.value;
        const age = e.target.age.value;
        alert(`Tên của bạn là: ${name}, tuổi của bạn là: ${age}`);
      }
      }>
        <h1>Form nhập thông tin</h1>
        <label>
          Nhập tên của bạn:
          <input type="text" name="name" />
        </label>
        <br />
        <label>
          Nhập tuổi của bạn:
          <input type="number" name="age" />
        </label>
        <br />
        <button type="submit">Gửi</button>
      </form>
      <input onKeyDown={(e) => {
        if (e.key === 'Enter') {
          alert(`Bạn đã nhập: ${e.target.value}`);
        }
      }
      } placeholder='Nhấn Enter để gửi' />
      <p>Đây là dòng của onKeyDown</p>
      <input onKeyUp={(e) => {
        if (e.key === 'Enter') {
          alert(`Bạn đã nhập: ${e.target.value}`);
        }
      }
      } placeholder='Nhấn Enter để gửi' />
      <p>Đây là dòng của onKeyUp</p>
      {/* <div onMouseEnter={() => alert('Bạn đã di chuột vào đây')} onMouseLeave={() => alert('Bạn đã rời chuột ra khỏi đây')}>
        <p>Di chuột vào đây để xem thông báo</p>
      </div> */}
      {/* <input onFocus={() => alert('Bạn đã tập trung vào ô nhập này')} onBlur={() => alert('Bạn đã rời khỏi ô nhập này')} placeholder='Nhập vào đây' />
      <p>Đây là dòng của onFocus và onBlur</p> */}
      <button onDoubleClick={() => alert('Bạn đã nhấp đúp vào nút này')}>Nhấp đúp vào đây</button>
      {/* <div onScroll={(e) => {
        const scrollTop = e.target.scrollTop;
        alert(`Bạn đã cuộn trang xuống: ${scrollTop}px`);
      }} style={{ height: '200px', overflowY: 'scroll', border: '1px solid black' }}>
        <p>Cuộn xuống để xem thông báo</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
      </div> */}
    </>
  )
}
function Welcome(props) {
  return <h1>Xin chào {props.name}! với {props.age} đây là props thứ 1</h1>;
}

function Hello(props){
  return <h1>Xin chào {props.name} với {props.age} đây là props thứ 2</h1>
}
export { Hello }
export { Welcome }
export default App
