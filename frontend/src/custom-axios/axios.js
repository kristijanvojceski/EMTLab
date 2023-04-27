import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:9092/api',
    headers: {
        'Access-control-allow-origin':'*'
    }
})

export default instance