import { render, screen } from '@testing-library/react';
import MyComponent from './MyComponent';
import '@testing-library/jest-dom'


test('mostra uma lista de diretórios', () => {
  
  const directories = [
    { id: 1, nome: 'Documentos', files: [{ id: 1, nome: 'file1.txt' }] },
    { id: 2, nome: 'Imagens', files: [{ id: 2, nome: 'image1.jpg' }] },
  ];

  
  render(<MyComponent directories={directories} />);


  expect(screen.getByText('Documentos - 1 arquivos')).toBeInTheDocument();
  expect(screen.getByText('Imagens - 1 arquivos')).toBeInTheDocument();
});

test('mostra mensagem quando não há diretórios', () => {
  render(<MyComponent directories={[]} />);

  
  expect(screen.getByText('Nenhum diretório encontrado')).toBeInTheDocument();
});