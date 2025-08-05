import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  id: bigint;
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

export interface cedulaDuplicadaResponse {
  error: string;
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
  agregarUsuario(usuario: UsuarioRequest): Observable<any> {
      return this.http.post<any>(this.apiUrl, usuario);
  }
  actualizarUsuario(cedula: bigint, usuario: UsuarioRequest): Observable<UsuarioResponse> {
      return this.http.put<UsuarioResponse>(`${this.apiUrl}/${cedula}`, usuario);
  }
  eliminarUsuario(cedula: bigint): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${cedula}`);
  }

}
