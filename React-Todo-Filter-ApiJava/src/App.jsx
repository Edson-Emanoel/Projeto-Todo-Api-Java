import { useState, useEffect } from 'react'

import Todo from './components/todo';
import TodoForm from './components/TodoForm';
import Search from './components/Search';
import Filter from './components/Filter';
import axios from 'axios';  

import './App.css'

function App() {
  const urlBase = "http://localhost:8050/api/tarefas/todos";

  const [todos, setTodos] = ([])

  const [search, setSearch] = useState("")

  const [filter, setFilter] = useState("All")
  const [sort, setSort] = useState("Asc")

  useEffect(() => {
    axios.get(`${urlBase}`)
      .then(res => setTodos(res.data))
  }, [todos, setTodos]) 

  const addTodo = (text, category) => { 
    axios.post(`${urlBase}/cadastrar`, {
      text,
      category
    })
  }

  const removeTodo = (id) => {
    const newTodos = [...todos]
    const filteredTodos = newTodos.filter((todo) => {
      todo.id !== id ? todo : null
    })
    setTodos(filteredTodos)
  }

  const completeTodo = (id) => {
    const newTodos = [...todos]
    newTodos.map((todo) => todo.id === id ? todo.isCompleted = !todo.isCompleted : todo)
    setTodos(newTodos)
  }

  return <div className="app">
    <h1>Lista de Tarefas</h1>
    <Search search={search} setSearch={setSearch} />
    <Filter filter={filter} setFilter={setFilter} setSort={setSort} />
    <div className="todo-list">
      {todos
        .filter((todo) =>
          filter === "All"
            ? true
            : filter === "Completed"
            ? todo.isCompleted
            : !todo.isCompleted
        ) 
        .filter((todo) =>
          todo.text.toLowerCase().includes(search.toLowerCase())
        )
        .sort((a,b) =>
          sort === "Asc"
          ? a.text.localeCompare(b.text)
          : b.text.localeCompare(a.text)
        )
        .map((todo) => (
          <Todo
            key={todo.id}
            todo={todo}
            removeTodo={removeTodo}
            completeTodo={completeTodo}
          />
      ))}
    </div>
    <TodoForm addTodo={addTodo} />
  </div>
}

export default App