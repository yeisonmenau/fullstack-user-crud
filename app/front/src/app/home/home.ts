import { Component, input, InputSignal, OnInit } from '@angular/core';
import { Api, UsuarioResponse, UsuarioRequest } from '../servicios/api';
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
  usuarioEditando: any | null = null;

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
  trackByCedula(index: number, item: any) {
    return item.cedula;
  }

  editarUsuario(usuario: UsuarioResponse): void {
    Swal.fire({
      title: 'Editar usuario',
      html: `
        <input id="nombre" class="swal2-input" value="${usuario.nombre}">
        <input id="correo" class="swal2-input" value="${usuario.correo}">
        <input id="fechaNacimiento" type="date" class="swal2-input">
      `,
      showCancelButton: true,
      confirmButtonText: 'Guardar',
      preConfirm: () => ({
        cedula: Number(usuario.cedula),
        nombre: (document.getElementById('nombre') as HTMLInputElement).value,
        correo: (document.getElementById('correo') as HTMLInputElement).value,
        fechaNacimiento: (document.getElementById('fechaNacimiento') as HTMLInputElement).value
      })
    }).then(res => {
      if (res.isConfirmed) {
        this.api.actualizarUsuario(usuario.cedula, res.value).subscribe(r => {
          this.datos = this.datos.map(u => u.cedula === r.cedula ? r : u);
          Swal.fire('Éxito', 'Usuario actualizado', 'success');
        });
      }
    });
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
