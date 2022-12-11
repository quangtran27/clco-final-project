using GUI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Net;
using System.Text.Json;

namespace GUI.Controllers
{
    public class StudentController : Controller
    {
        // GET: StudentController
        public ActionResult Index()
        {
            var url = "http://34.201.150.254:8080/student/get-all";

            var request = WebRequest.Create(url);
            request.Method = "GET";

            using var webResponse = request.GetResponse();
            using var webStream = webResponse.GetResponseStream();

            using var reader = new StreamReader(webStream);
            var json_data = reader.ReadToEnd();

            var list = JsonSerializer.Deserialize<IList<StudentModel>>(json_data);
            var studentList = new List<StudentModel>();

            if (list != null)
            {
                foreach (var st in list)
                {
                    var student = new StudentModel();
                    student.id = st.id;
                    student.fullName = st.fullName;
                    student.gender = st.gender;
                    student.major = st.major;
                    student.birthday = st.birthday;
                    student.gpa = st.gpa;
                    studentList.Add(student);
                }
            }
            return View(studentList);
        }

        // GET: StudentController/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: StudentController/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: StudentController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: StudentController/Edit/5
        public ActionResult Edit(int id)
        {
            var url = "http://34.201.150.254:8080/student/get?id=" + id.ToString();

            var request = WebRequest.Create(url);
            request.Method = "GET";

            using var webResponse = request.GetResponse();
            using var webStream = webResponse.GetResponseStream();

            using var reader = new StreamReader(webStream);
            var json_data = reader.ReadToEnd();
            var json = JsonSerializer.Deserialize<StudentModel>(json_data);
            var student = new StudentModel();

            if (json != null)
            {
                student.id = json.id;
                student.fullName = json.fullName;
                student.gender = json.gender;
                student.major = json.major;
                student.birthday = json.birthday;
                student.gpa = json.gpa;
            }
            return View(student);
        }

        // POST: StudentController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: StudentController/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: StudentController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, IFormCollection collection)
        {
            try
            {
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}
