import { Component, OnInit } from '@angular/core';
import { Api, UsuarioResponse, UsuarioRequest } from '../servicios/api';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  datos: UsuarioResponse[] = [];

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.api.obtenerDatos().subscribe({
      next: (respuesta) => { this.datos = respuesta; },
      error: (error) => { console.error('Error al obtener los datos:', error); }
    });
  }

  agregarNuevoUsuario(): void {
    const nuevoUsuario: UsuarioRequest = {
      cedula: 101010,
      nombre: 'quemado',
      correo: 'quemado@mail.com',
      fechaNacimiento: '2020-02-02'
    };
    this.api.agregarUsuario(nuevoUsuario).subscribe({
      next: (respuesta) => {
        console.log('Usuario agregado:', respuesta);
        this.datos = [...this.datos, respuesta];
      },
      error: (error) => { console.error('Error al agregar usuario:', error); }
    });
  }

  actualizarUsuario(cedula: number = 101010): void {
    const nuevoUsuario: UsuarioRequest = {
      cedula: 1010101,
      nombre: 'quemado1',
      correo: 'quemado1@mail.com',
      fechaNacimiento: '2020-02-02'
    };
    this.api.actualizarUsuario(cedula, nuevoUsuario).subscribe({
      next: (respuesta) => {
        this.datos = this.datos.map(usuario => usuario.cedula === BigInt(cedula) ? respuesta : usuario);
        console.log('Usuario actualizado:', respuesta);
        this.datos = [...this.datos];
      },
      error: (error) => { console.error('Error al actualizar usuario:', error); }
    });
  }

  eliminarUsuario(cedula: number = 9090): void {
    this.api.eliminarUsuario(cedula).subscribe({
      next: (respuesta) => {
        console.log('Usuario eliminado:', respuesta);
        this.datos = this.datos.filter(usuario => usuario.cedula !== BigInt(cedula));
        this.datos = [...this.datos];
      },
      error: (error) => { console.error('Error al eliminar usuario:', error); }
    });
  }
}
