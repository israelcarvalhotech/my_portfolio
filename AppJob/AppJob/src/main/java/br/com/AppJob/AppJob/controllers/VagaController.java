package br.com.AppJob.AppJob.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.AppJob.AppJob.models.Candidatos;
import br.com.AppJob.AppJob.models.Vaga;
import br.com.AppJob.AppJob.repository.CandidatosRepository;
import br.com.AppJob.AppJob.repository.VagaRepository;

@Controller
public class VagaController {

	private VagaRepository vr;
	private CandidatosRepository cr;

	@RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
	public String form() {
		return "vaga/formVaga";
	}

	@RequestMapping(value = "/cadastarVaga", method = RequestMethod.POST)
	public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarVaga";
		}

		vr.save(vaga);
		attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
		return "redirect:/cadastrarVaga";
	}

	// LISTA DE VAGAS
	@RequestMapping("/vagas")
	public ModelAndView listaVagas() {
		ModelAndView mv = new ModelAndView("vaga/listaVaga");
		Iterable<Vaga> vagas = vr.findAll();
		mv.addObject("vagas", vagas);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
		mv.addObject("vaga", vaga);

		Iterable<Candidatos> candidatos = cr.findByVaga(vaga);
		mv.addObject("candidatos", candidatos);
		return mv;

	}

	// DELETAR VAGA
	@RequestMapping("/deletarVaga")
	public String deletarVaga(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		vr.delete(vaga);
		return "redirect:/vagas";
	}

	public String detalhesVagasPost(@PathVariable("codigo") long codigo, @Valid Candidatos candidatos,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{codigo}";
		}

		if (cr.findByRg(candidatos.getRg()) != null) {
			attributes.addFlashAttribute("mensagem erro", "RG duplicado");
			return "redirect:/{codigo}";
		}

		Vaga vaga = vr.findByCodigo(codigo);
		candidatos.setVaga(vaga);
		cr.save(candidatos);
		attributes.addFlashAttribute("mensagem", "Canditado adicionado com sucesso!");
		return "redirect:/{codigo}";

	}

	@RequestMapping("/deletarCandidatos")
	public String deletarCandidatos(String rg) {
		Candidatos candidatos = cr.findByRg(rg);
		Vaga vaga = candidatos.getVaga();
		String codigo = "" + vaga.getCodigo();
		cr.delete(candidatos);
		return "redirect:/" + codigo;
	}

	// FORMULARIO DE EDIÇÃO DE VAGA

	@RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/update-vaga");
		mv.addObject("vaga", vaga);
		return mv;
	}

	// UPDATE DA VAGA

	@RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		vr.save(vaga);
		attributes.addFlashAttribute("success", "Vaga alterada com sucesso!");
		long codigoLong = vaga.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}
