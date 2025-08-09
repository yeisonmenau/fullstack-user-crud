import { Component, input, InputSignal, OnInit } from '@angular/core';
import { Api, UsuarioResponse, UsuarioRequest, cedulaDuplicadaResponse } from '../servicios/api';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit{

  datos: UsuarioResponse[] = [];
  inputCedula: InputSignal<bigint | undefined> = input<bigint>();
  inputNombre: InputSignal<string | undefined> = input<string>();
  inputCorreo: InputSignal<string | undefined> = input<string>();
  inputFecha: InputSignal<string | undefined> = input<string>();
  mostrarFormulario: boolean = false;

  constructor(private api: Api) {}
  
  ngOnInit(): void {
    this.api.obtenerDatos().subscribe({
      next: (respuesta) => {
        this.datos = respuesta;
      },
      error: (error) => {
        console.error('Error al obtener los datos:', error);
      }
    });
  }

  agregarNuevoUsuario(
    cedula: string, 
    nombre: string, 
    correo: string, 
    fechaNacimiento: string
  ): void {

    const numeroCedula = Number(cedula);

    if (isNaN(numeroCedula)) {
      Swal.fire('Cédula inválida', 'La cédula debe ser un número válido.', 'warning');
      return;
    }

    const nuevoUsuario: UsuarioRequest = {
      cedula: numeroCedula,
      nombre,
      correo,
      fechaNacimiento
    };

    // Validar campos vacíos
    const camposInvalidos = Object.entries(nuevoUsuario)
      .filter(([_, valor]) => valor == null || valor === '')
      .map(([campo]) => campo);

    if (camposInvalidos.length > 0) {
      Swal.fire(
        'Campos incompletos',
        `Por favor completa los siguientes campos: ${camposInvalidos.join(', ')}`,
        'warning'
      );
      return;
    }

    // Llamada al servicio
    this.api.agregarUsuario(nuevoUsuario).subscribe({
      next: (respuesta) => {       
        if (respuesta.error) {
          Swal.fire('Error', respuesta.error, 'error');
          return;
        }

        // Agregar el nuevo usuario y cerrar el formulario
        this.datos = [...this.datos, respuesta];
        this.mostrarFormulario = false;

        Swal.fire('Éxito', 'Usuario agregado correctamente.', 'success');
        console.log('Usuario agregado correctamente:', respuesta);
      },
      error: (error) => {
        console.error('Error al agregar usuario:', error);
        Swal.fire('Error', 'No se pudo agregar el usuario.', 'error');
      }
    });
  }

  actualizarUsuario(cedula: bigint): void {
      const nuevoUsuario: UsuarioRequest = {
      cedula: 101010,
      nombre: 'quemado',
      correo: 'quemado@mail.com',
      fechaNacimiento: '2020-02-02'
      };
  this.api.actualizarUsuario(cedula, nuevoUsuario).subscribe({
    next: (respuesta) => {
      this.datos = this.datos.map(usuario => usuario.cedula === cedula ? respuesta : usuario);
    } ,
    error: (error) => {
      console.error('Error al actualizar usuario:', error);
    }
  })
  }

  eliminarUsuario(cedula: bigint): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción eliminará el usuario de la base de datos.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#e7000b',
    }).then((result) => {
      if (result.isConfirmed) {
        this.api.eliminarUsuario(cedula).subscribe({
          next: () => {
            this.datos = this.datos.filter(usuario => usuario.cedula !== cedula);
            Swal.fire('Eliminado', 'El usuario ha sido eliminado correctamente', 'success');
          },
          error: () => {
            Swal.fire('Error', 'No se pudo eliminar el usuario', 'error');
          }
        });
      }
    });
  }



}
