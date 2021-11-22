import http from '@/utils/http'


export const loginPost=(data) => {
  return http.post("/login",data)
}
