import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  id: number;
  cedula: bigint;
  nombre: string;
  correo: string;
  edad: number;
}
export interface UsuarioRequest {
  cedula: number;
  nombre: string;
  correo: string;
  fechaNacimiento: string;
}


@Injectable({
  providedIn: 'root'
})
export class Api {

  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  obtenerDatos(): Observable<UsuarioResponse[]> {
      return this.http.get<UsuarioResponse[]>(this.apiUrl);
  }
  agregarUsuario(usuario: UsuarioRequest): Observable<UsuarioResponse> {
      return this.http.post<UsuarioResponse>(this.apiUrl, usuario);
  }
  actualizarUsuario(cedula: number, usuario: UsuarioRequest): Observable<UsuarioResponse> {
      return this.http.put<UsuarioResponse>(`${this.apiUrl}/${cedula}`, usuario);
  }
  eliminarUsuario(cedula: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${cedula}`);
  }

}
