import useAxiosPrivate from "../hooks/useAxios";
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const TodoList = () => {
  const [todos, setTodos] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate()
  const axios= useAxiosPrivate();
  const getTodos = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/todos', {
        withCredentials: true,
        headers: {
          'Content-Type': 'application/json',
          // Otros encabezados según sea necesario
        },
      });
      console.log(response.data)
      setTodos(response.data);
    } catch (error) {
      setError(error.message || 'Ha ocurrido un error.');
    }
  };
  const deleteTodo = async (id)=> {
    try {
        const response = await axios.delete(`http://localhost:8080/api/todos/${id}`);
        getTodos()
      } catch (error) {
        setError(error.message || 'Ha ocurrido un error.');
      }
  }

  useEffect(() => {
    getTodos();
  }, []);

  const Error = () => (
    <p className='p-2 bg-red-500 text-white font-mono text-sm'>{error}</p>
  );


  return (
    <div className="bg-slate-500 h-screen">
      <div className="bg-slate-700">
        <div className="h-14 flex bg-indigo-500">
          <h1 className="p-3 text-white">Todolist App</h1>
          <button className='text-white bg-green-600 w-24 ml-5' onClick={() => navigate("/todos/create")}>Add ToDo</button>
          <div className="flex ml-auto">
            <button className='text-white bg-red-600 ml-5 w-24 mr-2' onClick={() => navigate("/")}>Logout</button>
          </div>
        </div>
      </div>
      {error && <Error />}
      <div className='flex flex-wrap justify-center'>
        {todos &&
          todos.map(todo => (
            <div
              className='flex p-2 border-black border-2 dark:border-none ml-10 bg-indigo-500 hover:bg-slate-400 m-2 rounded-md flex-col  cursor-pointer hover:duration-300 ease-linear duration-300 mt-5 w-72 h-80 '
              key={todo.id}
            >
              <button
                className='bg-red-600 rounded border-2 w-6'
                onClick={() => deleteTodo(todo.id)} // Pasa el id al hacer clic
              >X</button>
              <div className='flex flex-row '>
                <div className='space-y-4'>
                  <h2 className='text-xl text-white'>Title:</h2>
                  <h2 className='text-xl font-semibold  rounded bg-white ml-20'>
                    {todo.title}
                  </h2>
                  <p className=' text-white rounded text-xl'>Description:</p>
                  <p className='text-xl  font-semibold  bg-white rounded ml-20 '>
                    {todo.description}
                  </p>
                  <p className=' text-white rounded text-xl'>Date:</p>
                  <p className='text-xl  font-semibold  bg-white rounded ml-20 '>
                    {todo.fecha}
                  </p>
                </div>
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default TodoList;