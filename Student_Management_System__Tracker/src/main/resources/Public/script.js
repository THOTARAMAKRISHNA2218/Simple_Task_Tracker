const API_URL = 'http://localhost:8081/students'; 
document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('taskForm');
  const searchButton = document.getElementById('searchButton');
  const resetButton = document.getElementById('resetButton');

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const title = document.getElementById('title').value;
    const status = document.getElementById('status').value;

    try {
      await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, status }),
      });

      form.reset();
      loadTasks(); 
    } catch (err) {
      console.error('Failed to add task:', err);
    }
  });

  searchButton.addEventListener('click', searchTask); 
  resetButton.addEventListener('click', loadTasks); 

  loadTasks(); 
});


async function loadTasks() {
  try {
    const res = await fetch(API_URL);
    const tasks = await res.json();

    const list = document.getElementById('taskList');
    list.innerHTML = '';

    tasks.forEach((task) => {
      const li = document.createElement('li');
      li.innerHTML = `
        <span>
          <strong>${task.title}</strong> — ${task.status} (${new Date(task.createdDate).toLocaleDateString()})
        </span>
        <div class="actions">
          ${task.status === 'PENDING' ? `<button class="done" onclick="markDone(${task.id})">✔ Done</button>` : ''}
          <button onclick="deleteTask(${task.id})">🗑 Delete</button>
        </div>
      `;
      list.appendChild(li);
    });
  } catch (err) {
    console.error('Failed to load tasks:', err);
  }
}

// Delete task
async function deleteTask(id) {
  try {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    loadTasks(); 
  } catch (err) {
    console.error('Failed to delete task:', err);
  }
}


async function markDone(id) {
  try {
    await fetch(`${API_URL}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ status: 'DONE' }),
    });
    loadTasks(); 
  } catch (err) {
    console.error('Failed to mark task as done:', err);
  }
}


async function searchTask() {
  const title = document.getElementById('searchTitle').value;
  if (!title) return; 

  try {
    const res = await fetch(`${API_URL}/search/${title}`);
    const tasks = await res.json();

    const list = document.getElementById('taskList');
    list.innerHTML = ''; 

    if (tasks.length === 0) {
      list.innerHTML = '<li>No tasks found</li>';
    } else {
      tasks.forEach((task) => {
        const li = document.createElement('li');
        li.innerHTML = `
          <span>
            <strong>${task.title}</strong> — ${task.status} (${new Date(task.createdDate).toLocaleDateString()})
          </span>
          <div class="actions">
            ${task.status === 'PENDING' ? `<button class="done" onclick="markDone(${task.id})">✔ Done</button>` : ''}
            <button onclick="deleteTask(${task.id})">🗑 Delete</button>
          </div>
        `;
        list.appendChild(li);
      });
    }
  } catch (err) {
    console.error('Task not found:', err);
    alert('Task not found!');
  }
}
