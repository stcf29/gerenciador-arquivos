import { render, screen } from '@testing-library/react';
import MyComponent from './MyComponent';
import '@testing-library/jest-dom'


test('mostra uma lista de diretórios', () => {
  // Simula os diretórios que serão passados como props
  const directories = [
    { id: 1, nome: 'Documentos', files: [{ id: 1, nome: 'file1.txt' }] },
    { id: 2, nome: 'Imagens', files: [{ id: 2, nome: 'image1.jpg' }] },
  ];

  // Renderiza o componente com os diretórios
  render(<MyComponent directories={directories} />);

  // Verifica se os nomes dos diretórios aparecem na tela
  expect(screen.getByText('Documentos - 1 arquivos')).toBeInTheDocument();
  expect(screen.getByText('Imagens - 1 arquivos')).toBeInTheDocument();
});

test('mostra mensagem quando não há diretórios', () => {
  render(<MyComponent directories={[]} />);

  // Verifica se a mensagem de "Nenhum diretório encontrado" aparece
  expect(screen.getByText('Nenhum diretório encontrado')).toBeInTheDocument();
});