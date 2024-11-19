package com.teamten.planilha_certa.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.teamten.planilha_certa.ClassTB3Contrato.Contrato;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorAlta;
import com.teamten.planilha_certa.ClassTB3Contrato.ContratoPriorBaixa;
import com.teamten.planilha_certa.ClassTB1Cliente.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContratoService {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME_CONTRATOS = "contratos";
    private static final String COLLECTION_NAME_CLIENTES = "clientes";
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void init() {
        initializeIdCounter();
    }

    private void initializeIdCounter() {
        try {
            CollectionReference contratos = firestore.collection(COLLECTION_NAME_CONTRATOS);
            ApiFuture<QuerySnapshot> query = contratos.get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            long maxId = 0;
            for (QueryDocumentSnapshot document : documents) {
                ContratoPriorAlta contrato = document.toObject(ContratoPriorAlta.class);
                maxId = Math.max(maxId, contrato.getIdContrato());
            }

            idCounter.set(maxId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ID counter", e);
        }
    }

    public ContratoPriorAlta cadastrarContratoPriorAlta(ContratoPriorAlta contrato) {
        try {
            // Buscar cliente pelo CPF
            CollectionReference clientesCollection = firestore.collection(COLLECTION_NAME_CLIENTES);
            Query clienteQuery = clientesCollection.whereEqualTo("cpfCliente", contrato.getCpfCliente());
            ApiFuture<QuerySnapshot> clienteQuerySnapshot = clienteQuery.get();
            List<QueryDocumentSnapshot> clienteDocuments = clienteQuerySnapshot.get().getDocuments();

            if (!clienteDocuments.isEmpty()) {
                // Assumindo que o CPF é único, pegamos o primeiro resultado
                DocumentSnapshot clienteDocument = clienteDocuments.get(0);
                Cliente cliente = clienteDocument.toObject(Cliente.class);

                // Verificar se o cliente é Vip
                if ("Vip".equalsIgnoreCase(cliente.getCategoriaCliente())) {
                    // Preencher idCliente e nomeCliente no contrato
                    contrato.setIdCliente(cliente.getIdCliente());
                    contrato.setNomeCliente(cliente.getNomeCliente());

                    // Atualizar pontos do cliente
                    int novosPontos = cliente.getPontos() + 10;
                    cliente.setPontos(novosPontos);

                    // Aplicar desconto se os pontos forem 50
                    if (novosPontos >= 20) {
                        contrato.setDesconto(contrato.getValorServico() * 0.5f);
                        novosPontos = 0; // Reiniciar pontos
                    } else {
                        contrato.setDesconto(0);
                    }
                    cliente.setPontos(novosPontos);

                    // Gerar novo ID para o contrato e atualizar prioridade
                    contrato.setIdContrato(idCounter.incrementAndGet());
                    contrato.setPrioridadeAtendimento("Alta");

                    // Atualizar histórico de contratos do cliente
                    String historicoExistente = cliente.getHistoricoContratos();
                    String novoHistorico = (historicoExistente == null || historicoExistente.isEmpty())
                            ? "Contrato " + contrato.getIdContrato()
                            : historicoExistente + ", Contrato " + contrato.getIdContrato();
                    cliente.setHistoricoContratos(novoHistorico);

                    // Salvar cliente com histórico atualizado no Firestore
                    ApiFuture<WriteResult> clienteUpdateFuture = clienteDocument.getReference().set(cliente);
                    clienteUpdateFuture.get();

                    // Salvar contrato com desconto aplicado no Firestore
                    CollectionReference contratos = firestore.collection(COLLECTION_NAME_CONTRATOS);
                    ApiFuture<WriteResult> futureContrato = contratos.document(String.valueOf(contrato.getIdContrato())).set(contrato);

                    futureContrato.get();
                    return contrato;
                } else {
                    throw new RuntimeException("Cliente não é Vip");
                }
            } else {
                throw new RuntimeException("Cliente com CPF informado não encontrado");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ContratoPriorBaixa cadastrarContratoPriorBaixa(ContratoPriorBaixa contrato) {
        try {
            // Buscar cliente pelo CPF
            CollectionReference clientesCollection = firestore.collection(COLLECTION_NAME_CLIENTES);
            Query clienteQuery = clientesCollection.whereEqualTo("cpfCliente", contrato.getCpfCliente());
            ApiFuture<QuerySnapshot> clienteQuerySnapshot = clienteQuery.get();
            List<QueryDocumentSnapshot> clienteDocuments = clienteQuerySnapshot.get().getDocuments();

            if (!clienteDocuments.isEmpty()) {
                // Assumindo que o CPF é único, pegamos o primeiro resultado
                DocumentSnapshot clienteDocument = clienteDocuments.get(0);
                Cliente cliente = clienteDocument.toObject(Cliente.class);

                // Verificar se o cliente é Vip
                if ("Padrão".equalsIgnoreCase(cliente.getCategoriaCliente())) {
                    // Preencher idCliente e nomeCliente no contrato
                    contrato.setIdCliente(cliente.getIdCliente());
                    contrato.setNomeCliente(cliente.getNomeCliente());

                    // Atualizar pontos do cliente
                    int novosPontos = cliente.getPontos() + 10;
                    cliente.setPontos(novosPontos);

                    // Aplicar desconto se os pontos forem 50
                    if (novosPontos >= 20) {
                        contrato.setDesconto(contrato.getValorServico() * 0.3f);
                        novosPontos = 0; // Reiniciar pontos
                    } else {
                        contrato.setDesconto(0);
                    }
                    cliente.setPontos(novosPontos);

                    // Gerar novo ID para o contrato e atualizar prioridade
                    contrato.setIdContrato(idCounter.incrementAndGet());
                    contrato.setPrioridadeAtendimento("Baixa");

                    // Atualizar histórico de contratos do cliente
                    String historicoExistente = cliente.getHistoricoContratos();
                    String novoHistorico = (historicoExistente == null || historicoExistente.isEmpty())
                            ? "Contrato " + contrato.getIdContrato()
                            : historicoExistente + ", Contrato " + contrato.getIdContrato();
                    cliente.setHistoricoContratos(novoHistorico);

                    // Salvar cliente com histórico atualizado no Firestore
                    ApiFuture<WriteResult> clienteUpdateFuture = clienteDocument.getReference().set(cliente);
                    clienteUpdateFuture.get();

                    // Salvar contrato com desconto aplicado no Firestore
                    CollectionReference contratos = firestore.collection(COLLECTION_NAME_CONTRATOS);
                    ApiFuture<WriteResult> futureContrato = contratos.document(String.valueOf(contrato.getIdContrato())).set(contrato);

                    futureContrato.get();
                    return contrato;
                } else {
                    throw new RuntimeException("Cliente não é Padrão");
                }
            } else {
                throw new RuntimeException("Cliente com CPF informado não encontrado");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contrato> listarContratos() {
        List<Contrato> listarContratos = new ArrayList<>();
        try {
            CollectionReference contratos = firestore.collection(COLLECTION_NAME_CONTRATOS);
            ApiFuture<QuerySnapshot> future = contratos.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Contrato Contrato = document.toObject(Contrato.class);
                listarContratos.add(Contrato);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listarContratos;
    }

    public boolean editarContrato(Contrato Contrato) {
        // Atualiza o contrato existente
        CollectionReference contrato = firestore.collection(COLLECTION_NAME_CONTRATOS);
        ApiFuture<WriteResult> future = contrato.document(String.valueOf(Contrato.getIdContrato())).set(Contrato);

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirContrato(long idContrato) {
        // Exclui o contrato pelo ID
        CollectionReference contratos = firestore.collection(COLLECTION_NAME_CONTRATOS);
        ApiFuture<WriteResult> future = contratos.document(String.valueOf(idContrato)).delete();

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}