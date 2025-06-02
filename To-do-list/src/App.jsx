import { useState } from 'react'
import { useEffect } from 'react'
import React from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css'

function App() {
  const [Edit, setEdit] = useState(false);
  const [tasks, setTasks] = useState([]);
  const [input,setInput]= useState('');
  const [id, setId] = useState(1);
  const [editId, setEditId] = useState(null);
  const [editInputValue, setEditInputValue] = useState('');
  
  useEffect(() => {
    fetch('http://localhost:3001/todos')
      .then((response) => response.json())
      .then((data) => {
        setTasks(data);
        const maxId = data.length > 0 ? Math.max(...data.map(task => task.id)) : 0;
        setId(maxId + 1); // 
      })
      .catch((error) => {
        console.error('Error fetching tasks:', error);
      });
  },[])
  const handleAddTask = () => {
    
    if (!input.trim()) {
      alert('Please enter a task');
      return;
    }
    const newTask = {
      task: input,
    };
  
    fetch('http://localhost:3001/todos', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newTask),
    })
      .then(res => res.json())
      .then(data => {
        setTasks(prev => [...prev, data]);
        setInput('');
      })
      .catch(err => console.error('Error adding task:', err));
      
  };
  const handleDeleteTask = (taskId) => {
    fetch(`http://localhost:3001/todos/${taskId}`, {
      method: 'DELETE',
    })
      .then(() => {
        setTasks(prev => prev.filter(task => task.id !== taskId));
      })
      .catch(err => console.error('Error deleting task:', err));
  }
  const handleEditTask = (taskId, updatedTask) => {
    fetch(`http://localhost:3001/todos/${taskId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedTask),
    })
      .then(res => {
        if (!res.ok) {
          throw new Error(`HTTP error! status: ${res.status} - ${res.statusText}`);
        }
        else{
          console.log('Task updated successfully');
        }
        return res.json();
      })
      .then(data => {
        setTasks(prev => prev.map(task => (task.id === taskId ? data : task)));
        setEditId(null);
        setEditInputValue('');
      })
      .catch(err => console.error('Error updating task:', err));
  }
  return (
    <>
      <div className="container ">
        <div className="bg-light p-5 shadow rounded-5">
          <h2 className="text-dark">To do list</h2>
          <div className="d-flex justify-content-center align-items-center gap-3 mt-4 w-100">
            <input type="text" className="form-control" placeholder="Enter your task here..." value={input} onChange={(e)=>setInput(e.target.value)} />
            <button className="btn btn-success w-50" onClick={handleAddTask} >Add Task</button>
          </div>
          <div>
            <h3 className="mt-4">Your Tasks</h3>
            <ul className="list-group">
              {tasks.map(task => (
                <li key={task.id} className="list-group-item d-flex justify-content-between align-items-center">
                  <div className="flex-grow-1 me-3">
                    {editId === task.id ? (
                      <input
                        type="text"
                        className="form-control text-secondary"
                        value={editInputValue}
                        onChange={e => setEditInputValue(e.target.value)}
                      />
                    ) : (
                      <span className="text-secondary">{task.task}</span>
                    )}
                  </div>
                  <div className="d-flex gap-2">
                    {editId === task.id ? (
                      <>
                        <button
                          className="btn btn-success btn-sm"
                          onClick={() => {
                            handleEditTask(task.id, { ...task, task: editInputValue });
                            setEditId(null);
                          }}
                        >
                          Save
                        </button>
                        <button className="btn btn-secondary btn-sm" onClick={() => setEditId(null)}>
                          Cancel
                        </button>
                      </>
                    ) : (
                      <>
                        <button
                          className="btn btn-primary btn-sm"
                          onClick={() => {
                            setEditId(task.id);
                            setEditInputValue(task.task);
                          }}
                        >
                          Edit
                        </button>
                        <button
                          className="btn btn-danger btn-sm"
                          onClick={() => handleDeleteTask(task.id)}
                        >
                          Delete
                        </button>
                      </>
                    )}
                  </div>
                </li>
              ))}
            </ul>

          </div>
        </div>
      </div>
    </>
  )
}

export default App
