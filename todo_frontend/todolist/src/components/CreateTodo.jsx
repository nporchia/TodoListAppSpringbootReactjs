import axios from 'axios';
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import useAxiosPrivate from '../hooks/useAxios';

const CreateTodo= () => {
    const [todo, setTodo] = useState({
        title: "",
        description: "",
    });
    const navigate = useNavigate();
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const axios = useAxiosPrivate();
   

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true)
        setError("")
        try {
            const response = await axios.post("http://localhost:8080/api/todos", todo);
            navigate(-1)
        } catch (error) {
            setError(error.response.data)
            setLoading(false)
        }
    }
    return (
        <div className="m-4 rounded-xl">
            <p className={'m-4 font-mono dark:text-white'}>Create your ToDo</p>
            <form onSubmit={handleSubmit} className="m-4 flex flex-col dark:bg-slate-900 bg-gray-100 border-gray-50 shadow-md rounded-md  p-12">
                <div className={"flex flex-col gap-5 p-2"}>
                    <label className=' font-mono dark:text-white'>Title</label>
                    <input
                        required
                        placeholder="Write a title"
                        className={"mb-2 p-4 rounded-xl shadow-md focus:border-1 focus:border-slate-800 focus:border focus:duration-300  dark:bg-slate-700  dark:text-white w-96"}
                        onChange={(event) => setTodo({...todo, title: event.target.value})}
                        name={"title"}
                    />

                    <label className=' font-mono dark:text-white'>Description</label>
                    <input
                        placeholder="Write a description"
                        required
                        className={"mb-2 p-4 rounded-xl shadow-md focus:border-1 focus:border-slate-800 focus:border focus:duration-300  dark:bg-slate-700  dark:text-white w-96"}
                        onChange={(event) => setTodo({...todo, description: event.target.value})}
                        name={"description"}
                     />
                </div>
                <button className={'ml-2 mt-4 p-4 w-fit font-mono text-xl bg-green-600 hover:bg-green-500 text-white rounded-md hover:bg-gray-400 hover:duration-300 ease-linear duration-300 '}>Save</button>
            </form>
        </div>


    );
}

export default CreateTodo;