async function hello() {
    console.log("Trước await");
    await new Promise(resolve => setTimeout(resolve, 2000)); // đợi 2 giây
    console.log("Sau await");
  }
  
  hello();
  