package com.practica.toko.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.*;


@Controller
public class ControllerPedido {
	
	private Usuario user;
	
	@RequestMapping("/verMisPedidos")
	public String mostrarPedidos(Model model,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		double preciototal = 0.0;
		int cont=0;
		model.addAttribute("haydatos", user.getListaPedidos().size());
		model.addAttribute("pedidos", user.getListaPedidos());
		for(Pedido p : user.getListaPedidos()) {
			model.addAttribute("id", p.getId());
			for(Producto pro : p.getProducto()) {
				preciototal += pro.getPrecio();
			}
		}
		
		return "mostrarPedidos";
	}
	@RequestMapping("/InfoPedido")
	public String mostrarPedido(Model model,@RequestParam (value= "id") int id,HttpSession session) {
		
		double preciototal=0;
		Pedido pedido=new Pedido();
		for(Pedido p:user.getListaPedidos()) {
			if(p.getId()==id) {
				pedido=p;
			}
		}
		model.addAttribute("haydatos", pedido.getId_producto().size()-1);
		model.addAttribute("productos", pedido.getProducto());
		for(Producto pro : pedido.getProducto()) {
			model.addAttribute("id", pro.getId());
			model.addAttribute("nombre", pro.getNombre());
			model.addAttribute("precio",pro.getPrecio());
			preciototal += pro.getPrecio();
		}
		
	
		return "InfoPedido";
	}

}
